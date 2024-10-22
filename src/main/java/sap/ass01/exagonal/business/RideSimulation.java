package sap.ass01.exagonal.business;

import sap.ass01.exagonal.business.observers.ModelObserver;
import sap.ass01.exagonal.business.observers.ModelObserverSource;
import sap.ass01.exagonal.services.EBikeService;
import sap.ass01.exagonal.services.RideService;
import sap.ass01.exagonal.services.Service;
import sap.ass01.exagonal.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class RideSimulation extends Thread implements ModelObserverSource {
	
	private final Ride ride;
	private final User user;
	private final List<Service<?, String>> services;

	private final List<ModelObserver> observers;

	private volatile boolean stopped;

	public RideSimulation(
			final Ride ride,
			final User user,
			final List<Service<?, String>> services
			) {
		this.ride = ride;
		this.user = user;
		this.services = services;
		this.observers = new ArrayList<>();
		this.stopped = false;
	}
	
	public void run() {
		var b = ride.getEBike();
		b.updateSpeed(1);
		
		var lastTimeDecreasedCredit = System.currentTimeMillis();
		user.decreaseCredit(1);

		var lastTimeChangedDir = System.currentTimeMillis();
		
		while (!stopped) {

			var l = b.getLocation();
			var d = b.getDirection();
			var s = b.getSpeed();
			b.updateLocation(l.sum(d.mul(s)));
			l = b.getLocation();
			if (l.x() > 200 || l.x() < -200) {
				b.updateDirection(new V2d(-d.x(), d.y()));
				if (l.x() > 200) {
					b.updateLocation(new P2d(200, l.y()));
				} else {
					b.updateLocation(new P2d(-200, l.y()));
				}
			}
			if (l.y() > 200 || l.y() < -200) {
				b.updateDirection(new V2d(d.x(), -d.y()));
				if (l.y() > 200) {
					b.updateLocation(new P2d(l.x(), 200));
				} else {
					b.updateLocation(new P2d(l.x(), -200));
				}
			}
			
			/* change dir randomly */
			
			var elapsedTimeSinceLastChangeDir = System.currentTimeMillis() - lastTimeChangedDir;
			if (elapsedTimeSinceLastChangeDir > 500) {
				double angle = Math.random()*60 - 30;
				b.updateDirection(d.rotate(angle));
				elapsedTimeSinceLastChangeDir = System.currentTimeMillis();
			}
			
			
			/* update credit */
			
			var elapsedTimeSinceLastDecredit = System.currentTimeMillis() - lastTimeDecreasedCredit;
			if (elapsedTimeSinceLastDecredit > 1000) {
				user.decreaseCredit(1);
				lastTimeDecreasedCredit = System.currentTimeMillis();
			}

			// NOTE: Notify the EBikeApp about the new position
			this.updateModel();
			this.notifyStepDone();

			try {
				Thread.sleep(20);
			} catch (Exception ignored) {}
			
		}
	}

	private void updateModel() {
		log("Updating model...");
		Service.filterIsInstance(services, RideService.class).stream().findFirst().ifPresent(s -> s.update(this.ride));
		Service.filterIsInstance(services, UserService.class).stream().findFirst().ifPresent(s -> s.update(this.user));
		Service.filterIsInstance(services, EBikeService.class).stream().findFirst().ifPresent(s -> s.update(this.ride.getEBike()));
	}

	public void stopSimulation() {
		stopped = true;
		this.ride.end();
		this.updateModel();
		this.notifyStepDone();
		interrupt();
	}

	@Override
	public void notifyStepDone() {
		this.observers.forEach(ModelObserver::update);
	}

	@Override
	public void attach(final ModelObserver observer) {
		log("Attaching an observer: " + observer);
		this.observers.add(observer);
	}

	private void log(final String msg) {
		System.out.println("[RideSimulation - " + this.ride.getId() + " ]: " + msg);
	}
}
