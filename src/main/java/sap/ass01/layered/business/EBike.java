package sap.ass01.layered.business;

public interface EBike {
    String getId();

    EBikeState getState();

    void rechargeBattery();

    int getBatteryLevel();

    void decreaseBatteryLevel(int delta);

    boolean isAvailable();

    void updateState(EBikeState state);

    void updateLocation(P2d newLoc);

    void updateSpeed(double speed);

    void updateDirection(V2d dir);

    double getSpeed();

    V2d getDirection();

    P2d getLocation();

    public enum EBikeState {AVAILABLE, IN_USE, MAINTENANCE}
}
