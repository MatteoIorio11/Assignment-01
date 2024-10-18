package sap.ass01.layered.services;

import sap.ass01.layered.persistence.Repository;

public interface Service<T> {
    // TODO: A specific service interact with a specific GUI
    // TODO: at each operation done, a possible operation from a repository can be done
    void add(T newValue);
    <R extends Repository<T, ?>> void addRepository(R repository);
}
