package sap.ass01.exagonal.services.observers;

import sap.ass01.exagonal.services.dto.DTO;

public interface InputObserver<D extends DTO> {
    void notifyUpdateRequested(D newValue);
}
