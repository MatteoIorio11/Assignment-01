package sap.ass01.layered.business;

import sap.ass01.layered.presentation.EBikeApp;
import sap.ass01.layered.services.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface Ride {
    String getId();
    void start(List<Service<?, String>> services);

    void end();

    Date getStartedDate();

    boolean isOngoing();

    Optional<Date> getEndDate();

    User getUser();

    EBike getEBike();
}
