package sap.ass01.layered.business;

import sap.ass01.layered.presentation.EBikeApp;

import java.util.Date;
import java.util.Optional;

public interface Ride {
    String getId();

    void start(EBikeApp app);

    void end();

    Date getStartedDate();

    boolean isOngoing();

    Optional<Date> getEndDate();

    User getUser();

    EBike getEBike();
}
