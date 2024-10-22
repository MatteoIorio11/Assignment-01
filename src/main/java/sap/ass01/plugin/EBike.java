package sap.ass01.plugin;

import com.fasterxml.jackson.annotation.JsonCreator;
import sap.ass01.exagonal.business.P2d;
import sap.ass01.exagonal.business.V2d;

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

    public enum EBikeState {
        AVAILABLE,
        IN_USE,
        MAINTENANCE;

        @JsonCreator
        public static EBikeState fromValue(String value) {
            // Normalize the string (e.g., lowercase) and map it to the enum
            return switch (value.toUpperCase()) {
                case "AVAILABLE" -> AVAILABLE;
                case "IN_USE" -> IN_USE;
                case "MAINTENANCE" -> MAINTENANCE;
                default -> throw new IllegalArgumentException("Unknown value: " + value);
            };
        }
    }
}
