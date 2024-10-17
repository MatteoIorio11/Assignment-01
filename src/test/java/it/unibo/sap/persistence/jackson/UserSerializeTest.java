package it.unibo.sap.persistence.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.Serializer;
import sap.ass01.layered.persistence.json.JacksonSerializer;
import sap.ass01.layered.persistence.repositories.UserRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserSerializeTest {
    private final Serializer<UserImpl, String> serializer = new JacksonSerializer<>(UserImpl.class);
    private final UserRepository userRepository = new UserRepository(serializer);

    @BeforeEach
    public void deleteFiles() {

    }
    @Test
    public void testSerialiseUser() {
        final UserImpl user = new UserImpl("miao");
        this.userRepository.save(user);
        final Optional<UserImpl> fileUser = this.userRepository.getObjectByID("miao");
        assertTrue(fileUser.isPresent());
        assertEquals(fileUser.get(), user);
    }

    @Test
    public void testSerialiseUsers() {
        final UserImpl user1 = new UserImpl("miao1");
        final UserImpl user2 = new UserImpl("miao2");
        final UserImpl user3 = new UserImpl("miao3");

        final List<UserImpl> users = List.of(
                user1,
                user2,
                user3
        );
        this.userRepository.saveAll(users);
        final Optional<UserImpl> fileUser = this.userRepository.getObjectByID("miao2");
        assertTrue(fileUser.isPresent());
        assertEquals(fileUser.get(), user2);
    }
}
