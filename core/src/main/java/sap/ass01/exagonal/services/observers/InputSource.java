package sap.ass01.exagonal.services.observers;

import sap.ass01.exagonal.services.dto.DTO;

public interface InputSource<D extends DTO> {
    void addObserver(InputObserver<D> obs);
}
