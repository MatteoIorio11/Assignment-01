package sap.ass01.layered.services;

import sap.ass01.layered.business.Ride;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.RideDTO;

public class RideService implements Service<Ride>, InputObserver<RideDTO> {
    public RideService() { }

    @Override
    public void notifyUpdateRequested(final RideDTO newValue) {

    }

    @Override
    public void add(final Ride newValue) {

    }
}
