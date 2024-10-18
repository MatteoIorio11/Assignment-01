package sap.ass01.layered.services;

import sap.ass01.layered.business.User;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.UserDTO;

public class UserService implements Service<User>, InputObserver<UserDTO> {

    public UserService() { }

    @Override
    public void notifyUpdateRequested(final UserDTO newValue) {
    }

    @Override
    public void add(final User newValue) {

    }
}
