package sap.ass01.layered.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sap.ass01.layered.presentation.RideSimulationControlPanel;
import sap.ass01.layered.presentation.EBikeApp;

import java.util.Date;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unknown properties like "available"
public class RideImpl implements Ride {
	@JsonProperty("startDate")
	private Date startedDate;
	@JsonProperty("endDate")
	private Optional<Date> endDate;
	@JsonProperty("user")
	private User user;
	@JsonProperty("bike")
	private EBike ebike;
	@JsonProperty("ongoing")
	private boolean ongoing;
	@JsonProperty("id")
	private String id;
	@JsonIgnore
	private RideSimulation rideSimulation;

	public RideImpl(String id, User user, EBike ebike) {
		this.id = id;
		this.startedDate = new Date();
		this.endDate = Optional.empty();
		this.user = user;
		this.ebike = ebike;
	}

	@JsonCreator
	public RideImpl(
			@JsonProperty("startDate") Date startedDate,
			@JsonProperty("endDate") Optional<Date> endDate,
			@JsonProperty("user") User user,
			@JsonProperty("bike") EBike ebike,
			@JsonProperty("ongoing") boolean ongoing,
			@JsonProperty("id") String id) {
		this.startedDate = startedDate;
		this.endDate = endDate;
		this.user = user;
		this.ebike = ebike;
		this.ongoing = ongoing;
		this.id = id;
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
