package sap.ass01.layered.presentation.observers;

public interface InputSource<T> {
    void addObserver(InputObserver<T> obs);
}
