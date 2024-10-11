package sap.ass01.layered.business.observers;

public interface Source {
    void notifyObservers();
    void attach(Observer observer);
}
