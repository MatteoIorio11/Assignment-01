package sap.ass01.layered.services;

public interface Service<X> {
    //TODO: A specific service interact with a specific GUI
    // TODO: at each operation done, a possible operation from a repository can be done
    void add(X newValue);
}
