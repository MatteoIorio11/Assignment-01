package sap.ass01.exagonal.business.observers;

public interface ModelObserverSource {
    void notifyStepDone();
    void attach(ModelObserver observer);
}
