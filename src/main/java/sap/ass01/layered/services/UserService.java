package sap.ass01.layered.services;

import sap.ass01.layered.services.dto.UserDTO;

public class UserService implements Service<UserDTO> {

    public UserService() { }

    @Override
    public void notifyUpdateRequested(final UserDTO newValue) {
    }

    @Override
    public void add(UserDTO newValue) {

    }
}
