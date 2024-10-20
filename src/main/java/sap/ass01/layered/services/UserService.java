package sap.ass01.layered.services;

import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.business.observers.ModelObserver;
import sap.ass01.layered.business.observers.ModelObserverSource;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.services.observers.InputObserver;
import sap.ass01.layered.services.dto.UserDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserService implements Service<User, String>, InputObserver<UserDTO>, ModelObserverSource {
    private static final int USER_DEFAULT_CREDIT = 100;

    private final List<Repository<User, String>> repositories;
    private final List<ModelObserver> observers;

    public UserService() {
        this.repositories = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void notifyUpdateRequested(final UserDTO newValue) {
        this.add(this.fromDTO(newValue));
    }

    private User fromDTO(final UserDTO dto) {
        final var user = new UserImpl(dto.id());
        user.rechargeCredit(USER_DEFAULT_CREDIT);
        return user;
    }

    @Override
    public void add(final User newValue) {
        this.repositories.forEach(r -> r.save(newValue));
        this.notifyObservers();
    }

    @Override
    public void update(final User updatedValue) {
        this.repositories.forEach(r -> r.update(updatedValue));
        this.notifyObservers();
    }

    @Override
    public Iterable<User> getAll() {
        return this.repositories.stream().findFirst().map(Repository::getAll).orElse(Collections.emptyList());
    }

    @Override
    public Optional<User> getById(String objectId) {
        return this.repositories.stream().findFirst().flatMap(r -> r.getObjectByID(objectId));
    }

    @Override
    public <R extends Repository<User, String>> void addRepository(final R repository) {
        this.repositories.add(repository);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(ModelObserver::update);
    }

    @Override
    public void attach(final ModelObserver observer) {
        this.observers.add(observer);
    }
}
