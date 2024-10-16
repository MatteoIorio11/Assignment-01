package sap.ass01.layered.presentation.observers;

import sap.ass01.layered.business.Ride;

public interface RideObserver extends InputObserver<Ride> {
    @Override
    void notifyUpdateRequested(Ride newValue);
}
