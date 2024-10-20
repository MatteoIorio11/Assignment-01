package sap.ass01.layered.services;

import sap.ass01.layered.business.observers.ModelObserver;
import sap.ass01.layered.business.observers.ModelObserverSource;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.services.dto.DTO;
import sap.ass01.layered.services.observers.InputObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractObserverService<D extends DTO, T> implements Service<T, String>, InputObserver<D>, ModelObserverSource {

    private final List<ModelObserver> observers;
    protected final List<Repository<T, String>> repositories;

    public AbstractObserverService() {
        this.observers = new ArrayList<>();
        this.repositories = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(ModelObserver::update);
    }

    @Override
    public void attach(final ModelObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void add(T newValue) {
        this.repositories.forEach(r -> r.save(newValue));
        this.notifyObservers();
    }

    @Override
    public void update(T updatedValue) {
        this.repositories.forEach(r -> r.update(updatedValue));
        this.notifyObservers();
    }

    @Override
    public Iterable<T> getAll() {
        return this.repositories.stream().findFirst().map(Repository::getAll).orElse(Collections.emptyList());
    }

    @Override
    public Optional<T> getById(String objectId) {
        return this.repositories.stream().findFirst().flatMap(r -> r.getObjectByID(objectId));
    }

    @Override
    public <R extends Repository<T, String>> void addRepository(R repository) {
        this.repositories.add(repository);
    }
}
