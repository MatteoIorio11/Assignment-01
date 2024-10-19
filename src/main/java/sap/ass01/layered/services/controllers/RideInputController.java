package sap.ass01.layered.services.controllers;

import sap.ass01.layered.business.RideSimulation;
import sap.ass01.layered.services.observers.ActionObserver;

public class RideInputController implements ActionObserver {

    private final RideSimulation rideSimulation;

    public RideInputController(final RideSimulation rideSimulation) {
        this.rideSimulation = rideSimulation;
    }

    @Override
    public void notifyUpdateRequested() {
        this.rideSimulation.stopSimulation();
    }
}
