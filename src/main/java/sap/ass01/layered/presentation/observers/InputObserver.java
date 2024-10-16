package sap.ass01.layered.presentation.observers;

public interface InputObserver<T> {
    void notifyUpdateRequested(T newValue);
}
