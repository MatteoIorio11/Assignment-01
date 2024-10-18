package sap.ass01.layered.services;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.EBikeDTO;

public class EBikeService implements Service<EBike>, InputObserver<EBikeDTO> {

    public EBikeService() { }

    @Override
    public void notifyUpdateRequested(final EBikeDTO newValue) {
    }

    @Override
    public void add(final EBike newValue) {

    }
}
