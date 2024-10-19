package sap.ass01.layered.services;

import sap.ass01.layered.persistence.Repository;

import java.util.Optional;

public interface Service<T, K> {
    // TODO: A specific service interact with a specific GUI
    // TODO: at each operation done, a possible operation from a repository can be done
    void add(T newValue);

    void update(T updatedValue);

    Iterable<T> getAll();

    Optional<T> getById(K objectId);

    <R extends Repository<T, K>> void addRepository(R repository);
}

