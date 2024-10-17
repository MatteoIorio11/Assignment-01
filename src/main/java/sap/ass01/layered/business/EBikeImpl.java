package sap.ass01.layered.business;

public class EBikeImpl implements EBike {
	private final String id;
	private EBikeState state;
	private P2d loc;
	private V2d direction;
	private double speed;
	private int batteryLevel;  /* 0..100 */
	
	public EBikeImpl(String id) {
		this.id = id;
		this.state = EBikeState.AVAILABLE;
		this.loc = new P2d(0,0);
		direction = new V2d(1,0);
		speed = 0;
	}
	
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
