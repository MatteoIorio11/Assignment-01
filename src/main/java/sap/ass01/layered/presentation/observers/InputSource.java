package sap.ass01.layered.presentation.observers;

import sap.ass01.layered.services.dto.DTO;

public interface InputSource<D extends DTO> {
    void addObserver(InputObserver<D> obs);
}
