package sap.ass01.layered.business.observers;

public interface ModelObserverSource {
    void notifyStepDone();
    void attach(ModelObserver observer);
}
