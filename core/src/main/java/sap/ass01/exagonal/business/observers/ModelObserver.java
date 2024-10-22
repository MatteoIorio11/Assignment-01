package sap.ass01.exagonal.business.observers;

public interface ModelObserver {
    void update();
    // TODO: Decide if put the Source inside the constructor
    void attachToSource(ModelObserverSource source);
}
