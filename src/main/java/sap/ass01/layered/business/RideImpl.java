package sap.ass01.layered.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sap.ass01.layered.persistence.Key;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.services.Service;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unknown properties like "available"
public class RideImpl implements Ride{
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
	private List<Repository<Ride, String>> repositories = new LinkedList<>();
	@JsonIgnore
	private RideSimulation rideSimulation;

	public RideImpl(String id, User user, EBike ebike) {
		this.id = id;
		this.startedDate = new Date();
		this.endDate = Optional.empty();
		this.user = user;
		this.ebike = ebike;
	}

	public RideImpl(String id, User user, EBike ebike, final List<Repository<Ride, String>> repositories) {
		this(id, user, ebike);
		ebike.update();
		this.repositories = repositories;
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
	public RideSimulation start(final List<Service<?, String>> services) {
		this.ongoing = true;
        this.rideSimulation = new RideSimulation(this, user, services);
		this.rideSimulation.start();
		return this.rideSimulation;
	}
	
	@Override
	public void end() {
		endDate = Optional.of(new Date());
		ongoing = false;
		// missing in original implementation
		this.ebike.updateState(EBike.EBikeState.AVAILABLE);
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

	@Key
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RideImpl ride = (RideImpl) o;
		return Objects.equals(id, ride.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public void injectRepository(Repository<Ride, String> repository) {
		this.repositories.add(repository);
	}
	@Override
	public void save() {
		this.repositories.forEach(repo -> repo.save(this));
	}

	@Override
	public void update() {
		this.repositories.forEach(repo -> repo.update(this));
	}
}
