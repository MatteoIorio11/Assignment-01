package sap.ass01.layered.services;

import sap.ass01.layered.persistence.Repository;

public interface Service<T, K> {
    // TODO: A specific service interact with a specific GUI
    // TODO: at each operation done, a possible operation from a repository can be done
    void add(T newValue);

    Iterable<T> getAll();

    <R extends Repository<T, K>> void addRepository(R repository);
}
