package sap.ass01.layered.services;

import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements Service<User, String>, InputObserver<UserDTO> {
    private static final int USER_DEFAULT_CREDIT = 100;

    private final List<Repository<User, String>> repositories;

    public UserService() {
        this.repositories = new ArrayList<>();
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
    }

    @Override
    public List<User> getAll() {
        final List<User> users = new ArrayList<>();
        this.repositories.stream().map(Repository::getAll).forEach(i -> i.forEach(users::add));
        return users.stream().distinct().toList();
    }

    @Override
    public Optional<User> getById(String objectId) {
        return this.repositories.stream().findFirst().flatMap(r -> r.getObjectByID(objectId));
    }

    @Override
    public <R extends Repository<User, String>> void addRepository(final R repository) {
        this.repositories.add(repository);
    }
}
