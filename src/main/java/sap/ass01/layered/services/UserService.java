package sap.ass01.layered.services;

import sap.ass01.layered.business.User;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.persistence.UserRepository;

import java.util.List;

public class UserService implements Service<User>{
    List<User> userList;
    final Repository<User> userRepository = new UserRepository();
    @Override
    public void add(User newValue) {
        userRepository.saveAll(userList);
    }
}
