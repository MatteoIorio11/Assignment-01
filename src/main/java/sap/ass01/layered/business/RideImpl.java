package sap.ass01.layered.business;

import sap.ass01.layered.presentation.RideSimulationControlPanel;
import sap.ass01.layered.presentation.EBikeApp;

import java.util.Date;
import java.util.Optional;

public class RideImpl implements Ride {

	private Date startedDate;
	private Optional<Date> endDate;
	private User user;
	private EBike ebike;
	private boolean ongoing;
	private String id;
	private RideSimulation rideSimulation;
	
	public RideImpl(String id, User user, EBike ebike) {
		this.id = id;
		this.startedDate = new Date();
		this.endDate = Optional.empty();
		this.user = user;
		this.ebike = ebike;
	}
	

	@Override
	public void start(EBikeApp app) {
		ongoing = true;
        rideSimulation = new RideSimulation(this, user, app);
        RideSimulationControlPanel ridingWindow = new RideSimulationControlPanel(this, app);
        ridingWindow.display();
        rideSimulation.start();
        
        
	}
	
	@Override
	public void end() {
		endDate = Optional.of(new Date());
		ongoing = false;
		rideSimulation.stopSimulation();
	}

	@Override
	public Date getStartedDate() {
		return startedDate;
	}

	@Override
	public boolean isOngoing() {
		return this.ongoing;
	}
	
	@Override
	public Optional<Date> getEndDate() {
		return endDate;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public EBike getEBike() {
		return ebike;
	}
	
	public String toString() {
		return "{ id: " + this.id + ", user: " + user.getId() + ", bike: " + ebike.getId() + " }";
	}

	@Override
	public String getId() {
		return this.id;
	}
}
