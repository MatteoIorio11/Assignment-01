package sap.ass01.layered.presentation.observers;

public interface InputSource {
    void addObserver(InputObserver<?> obs);
}
