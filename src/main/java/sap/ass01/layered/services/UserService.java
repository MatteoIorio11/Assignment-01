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
        this.add(this.fromDTO(newValue));
    }

    private User fromDTO(final UserDTO dto) {
        final var user = new UserImpl(dto.id());
        user.rechargeCredit(USER_DEFAULT_CREDIT);
        return user;
    }
}
