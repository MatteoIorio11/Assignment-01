package it.unibo.sap.persistence.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.json.Serializer;
import sap.ass01.layered.persistence.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserSerializeTest {
    private final UserRepository userRepository = new UserRepository(Serializer.userJSONSerializer());

    @BeforeEach
    public void deleteFiles() {

    }
    @Test
    public void testSerialiseUser() {
        final User user = new UserImpl("miao");
        this.userRepository.save(user);
        final Optional<User> fileUser = this.userRepository.getObjectByID("miao");
        assertTrue(fileUser.isPresent());
        assertEquals(fileUser.get(), user);
    }

    @Test
    public void testSerialiseUsers() {
        final User user1 = new UserImpl("miao1");
        final User user2 = new UserImpl("miao2");
        final User user3 = new UserImpl("miao3");

        final List<User> users = List.of(
                user1,
                user2,
                user3
        );
        this.userRepository.saveAll(users);
        final Optional<User> fileUser = this.userRepository.getObjectByID("miao2");
        assertTrue(fileUser.isPresent());
        assertEquals(fileUser.get(), user2);
    }
}
