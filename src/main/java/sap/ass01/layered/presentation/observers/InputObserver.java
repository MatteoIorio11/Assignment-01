package sap.ass01.layered.presentation.observers;

import sap.ass01.layered.services.dto.DTO;

public interface InputObserver<D extends DTO> {
    void notifyUpdateRequested(D newValue);
}
