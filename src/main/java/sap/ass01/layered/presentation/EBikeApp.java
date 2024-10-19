package sap.ass01.layered.presentation;

import sap.ass01.layered.business.*;
import sap.ass01.layered.services.ServiceProvider;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class EBikeApp {
   	// NOTE: Observers the RideSimulation
    private ConcurrentHashMap<String, EBike> bikes;
    private HashMap<String, User> users;
    private HashMap<String, Ride> rides;

	private final EBikeAppView view;
    
    private int rideId;
    
    public EBikeApp(final ServiceProvider serviceProvider){
		this.view = new EBikeAppView(serviceProvider);
        setupModel();
    }

    protected void setupModel() {
        bikes = new ConcurrentHashMap<>();
        users = new HashMap<>();
        rides = new HashMap<>();
        
        rideId = 0;
        this.addUser("u1");
        this.addEBike("b1", new P2d(0,0));
    }

    public void display() {
		this.view.display();
    }
        
    public void addEBike(String id, P2d loc) {
    	EBike bike = new EBikeImpl(id);
    	bike.updateLocation(loc);
    	bikes.put(id, bike);
    	log("added new EBike " + bike);
    	this.view.refreshView();
    }
    
    public EBike getEBike(String id) {
    	return bikes.get(id);
    }

    public void addUser(String id) {
    	User user = new UserImpl(id);
    	user.rechargeCredit(100);
    	users.put(id, user);
    	log("added new User " + user);
		this.view.refreshView();
    }

    public void startNewRide(String userId, String bikeId) {
    	rideId++;    	 
    	String idRide = "ride-" + rideId;
    	
    	var b = bikes.get(bikeId);
    	var u = users.get(userId);
    	var ride = new RideImpl(idRide, u, b);
    	b.updateState(EBikeImpl.EBikeState.IN_USE);
    	rides.put(idRide, ride);
    	ride.start(this);
        
        log("started new Ride " + ride);        
    }

    public void endRide(String rideId) {
    	var r = rides.get(rideId);
    	r.end();
    	rides.remove(rideId);
    }
    
    public Enumeration<EBike> getEBikes(){
    	return bikes.elements();
    }
        
    public Collection<User> getUsers(){
    	return users.values();
    }
    
	private void log(String msg) {
		System.out.println("[EBikeApp] " + msg);
	}

	public static void main(String[] args) {
		final var serviceProvider = new ServiceProvider();
		var w = new EBikeApp(serviceProvider);
		w.display();
	}
}
