package sap.ass01.layered.business.observers;

public interface ModelObserverSource {
    void notifyObservers();
    void attach(ModelObserver observer);
}
