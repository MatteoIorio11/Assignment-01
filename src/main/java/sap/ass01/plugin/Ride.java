package sap.ass01.plugin;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface Ride {
    String getId();
    RideSimulationInterface start(List<Service<?, String>> services);

    void end();

    Date getStartedDate();

    boolean isOngoing();

    Optional<Date> getEndDate();

    User getUser();

    EBike getEBike();
}
