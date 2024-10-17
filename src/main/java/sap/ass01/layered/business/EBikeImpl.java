package sap.ass01.layered.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sap.ass01.layered.persistence.Key;

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unknown properties like "available"
public class EBikeImpl implements EBike {
	@JsonProperty("id")
	private final String id;
	@JsonProperty("state")
	private EBikeState state;
	@JsonProperty("location")
	private P2d loc;
	@JsonIgnore()
	private V2d direction;
	@JsonProperty("speed")
	private double speed;
	@JsonProperty("battery")
	private int batteryLevel;  /* 0..100 */

	public EBikeImpl(String id) {
		this(id, EBikeState.AVAILABLE, new P2d(0, 0), 0, 100);
	}

	@JsonCreator()
	public EBikeImpl(@JsonProperty("id") String id,
					 @JsonProperty("state") EBikeState state,
					 @JsonProperty("location") P2d loc,
					 @JsonProperty("speed") double speed,
					 @JsonProperty("battery") int batteryLevel) {
		this.id = id;
		this.state = state;
		this.loc = loc;
		this.direction = new V2d(1,0);;
		this.speed = speed;
		this.batteryLevel = batteryLevel;
	}

	@Key
	@Override
	public String getId() {
		return id;
	}

	@Override
	public EBikeState getState() {
		return state;
	}
	
	@Override
	public void rechargeBattery() {
		batteryLevel = 100;
		state = EBikeState.AVAILABLE;
	}
	
	@Override
	public int getBatteryLevel() {
		return batteryLevel;
	}
	
	@Override
	public void decreaseBatteryLevel(int delta) {
		batteryLevel -= delta;
		if (batteryLevel < 0) {
			batteryLevel = 0;
			state = EBikeState.MAINTENANCE;
		}
		// NOTE: call the persistence layer
	}

	
	@Override
	public boolean isAvailable() {
		return state.equals(EBikeState.AVAILABLE);
	}

	@Override
	public void updateState(EBikeState state) {
		this.state = state;
	}
	
	@Override
	public void updateLocation(P2d newLoc) {
		loc = newLoc;
	}

	@Override
	public void updateSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public void updateDirection(V2d dir) {
		this.direction = dir;
	}
	
	@Override
	public double getSpeed() {
		return speed;
	}
	
	@Override
	public V2d getDirection() {
		return direction;
	}
	
	@Override
	public P2d getLocation(){
		return loc;
	}
	
	public String toString() {
		return "{ id: " + id + ", loc: " + loc + ", batteryLevel: " + batteryLevel + ", state: " + state + " }";
	}
	
}
