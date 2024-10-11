package sap.ass01.layered.business.observers;

public interface Observer {
    void update();
    // TODO: Decide if put the Source inside the constructor
    void attachToSource(Source source);
}
