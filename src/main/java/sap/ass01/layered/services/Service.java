package sap.ass01.layered.services;

import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.DTO;

public interface Service<D extends DTO> extends InputObserver<D> {
    // TODO: A specific service interact with a specific GUI
    // TODO: at each operation done, a possible operation from a repository can be done
    void add(D newValue);
}
