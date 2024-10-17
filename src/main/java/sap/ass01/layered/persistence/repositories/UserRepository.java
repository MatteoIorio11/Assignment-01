package sap.ass01.layered.persistence;

import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.json.JacksonSerializer;

import java.util.Optional;


public class UserRepository extends AbstractRepository<UserImpl, String>{
    public UserRepository(Serializer<UserImpl, String> serializer) {
        super(serializer);
    }

    public static void main(String[] args) {
        final UserImpl user = new UserImpl("miao");
        final UserImpl user1 = new UserImpl("miao1");
        final UserImpl user2 = new UserImpl("miao2");
        final Serializer<UserImpl, String> serializer = new JacksonSerializer<>(UserImpl.class);
        final Serializer<EBikeImpl, String> serializer1 = new JacksonSerializer<>(EBikeImpl.class);
        final UserRepository userRepository = new UserRepository(serializer);
//        userRepository.save(user);
//        userRepository.save(user1);
//        userRepository.save(user2);
//
        System.err.println(userRepository.getObjectByID("miao"));
    }
}
