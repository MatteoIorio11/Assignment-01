package sap.ass01.layered.services;

import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserService implements Service<User, String>, InputObserver<UserDTO> {

    private final List<Repository<User, String>> repositories;

    public UserService() {
        this.repositories = new ArrayList<>();
    }

    @Override
    public void notifyUpdateRequested(final UserDTO newValue) {
        this.add(this.fromDTO(newValue));
    }

    private User fromDTO(final UserDTO dto) {
        return new UserImpl(dto.id());
    }

    @Override
    public void add(final User newValue) {
        this.repositories.forEach(r -> r.save(newValue));
    }

    @Override
    public <R extends Repository<User, String>> void addRepository(final R repository) {
        this.repositories.add(repository);
    }
}
