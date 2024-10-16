package sap.ass01.layered.presentation.observers;

import sap.ass01.layered.business.EBike;

public interface EBikeObserver extends InputObserver<EBike> {
    @Override
    void notifyUpdateRequested(final EBike newValue);
}
