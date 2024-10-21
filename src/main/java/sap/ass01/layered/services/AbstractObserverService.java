package sap.ass01.layered.services;

import sap.ass01.layered.business.Business;
import sap.ass01.layered.business.observers.ModelObserver;
import sap.ass01.layered.business.observers.ModelObserverSource;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.services.dto.DTO;
import sap.ass01.layered.services.observers.InputObserver;

import java.util.*;

public abstract class AbstractObserverService<D extends DTO, T extends Business<T, String>> implements Service<T, String>, InputObserver<D>, ModelObserverSource {
    private final List<ModelObserver> observers;
    private final List<T> objects;
    protected final List<Repository<T, String>> repositories;

    public AbstractObserverService() {
        this.observers = new ArrayList<>();
        this.repositories = new ArrayList<>();
        this.objects = new LinkedList<>();
    }

    @Override
    public void notifyStepDone() {
        this.observers.forEach(ModelObserver::update);
    }

    @Override
    public void attach(final ModelObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void add(T newValue) {
        newValue.save();
        this.objects.add(newValue);
        this.notifyStepDone();
    }

    @Override
    public void update(T updatedValue) {
        updatedValue.update();
        this.objects.removeIf(obj -> obj.equals(updatedValue));
        this.objects.add(updatedValue);
        this.notifyStepDone();
    }

    @Override
    public Iterable<T> getAll() {
        this.objects.clear();
        this.repositories.stream().findFirst().map(Repository::getAll).orElse(Collections.emptyList()).forEach(this.objects::add);
        return this.objects;
    }

    @Override
    public Optional<T> getById(String object) {
        return this.objects.stream().filter(obj -> obj.getId().equals(object)).findFirst();
    }

    @Override
    public List<Repository<T, String>> getRepositories() {
        return this.repositories;
    }

    @Override
    public <R extends Repository<T, String>> void addRepository(R repository) {
        this.repositories.add(repository);
    }
}
