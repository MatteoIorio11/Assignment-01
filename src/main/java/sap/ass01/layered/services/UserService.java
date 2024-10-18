package sap.ass01.layered.services;

import sap.ass01.layered.business.User;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserService implements Service<User>, InputObserver<UserDTO> {

    private final List<Repository<User, ?>> repositories;

    public UserService() {
        this.repositories = new ArrayList<>();
    }

    @Override
    public void notifyUpdateRequested(final UserDTO newValue) {
        // Some logic for retrieving user given the dto
    }

    @Override
    public void add(final User newValue) {
        this.repositories.forEach(r -> r.save(newValue));
    }

    @Override
    public <R extends Repository<User, ?>> void addRepository(final R repository) {
        this.repositories.add(repository);
    }
}
