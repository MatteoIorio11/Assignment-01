package sap.ass01.exagonal.business;

import sap.ass01.exagonal.services.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface Ride {
    String getId();
    RideSimulation start(List<Service<?, String>> services);

    void end();

    Date getStartedDate();

    boolean isOngoing();

    Optional<Date> getEndDate();

    User getUser();

    EBike getEBike();
}
