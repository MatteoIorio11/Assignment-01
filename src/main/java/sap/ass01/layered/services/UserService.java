package sap.ass01.layered.services;

import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.services.dto.UserDTO;

public class UserService extends AbstractObserverService<UserDTO, User> {
    private static final int USER_DEFAULT_CREDIT = 100;

    public UserService() {
        super();
    }

    @Override
    public void notifyUpdateRequested(final UserDTO newValue) {
        final User user = this.fromDTO(newValue);
        if (this.getById(user.getId()).isEmpty()) {
            this.add(user);
        }else{
            System.err.println("User ID<<" + user.getId() + ">>, already exists.");
        }
    }

    private User fromDTO(final UserDTO dto) {
        final var user = new UserImpl(dto.id(), this.repositories);
        user.rechargeCredit(USER_DEFAULT_CREDIT);
        return user;
    }
}
