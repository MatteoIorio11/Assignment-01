package it.unibo.sap.persistence.jackson;

import org.junit.jupiter.api.Test;
import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.Serializer;
import sap.ass01.layered.persistence.json.JacksonSerializer;
import sap.ass01.layered.persistence.repositories.EBikeRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EBikeSerializeTest {
    private final Serializer<EBikeImpl, String> serializer = new JacksonSerializer<>(EBikeImpl.class);
    private final EBikeRepository eBikeRepository = new EBikeRepository(serializer);

    @Test
    public void testSerialiseUser() {
        final EBikeImpl ebike = new EBikeImpl("miao");
        this.eBikeRepository.save(ebike);
        final Optional<EBikeImpl> fileUser = this.eBikeRepository.getObjectByID("miao");
        assertTrue(fileUser.isPresent());
        assertEquals(fileUser.get(), ebike);
    }

    @Test
    public void testSerialiseUsers() {
        final EBikeImpl ebike1 = new EBikeImpl("miao1");
        final EBikeImpl ebike2 = new EBikeImpl("miao2");
        final EBikeImpl ebike3 = new EBikeImpl("miao3");

        final List<EBikeImpl> users = List.of(
                ebike1,
                ebike2,
                ebike3
        );
        this.eBikeRepository.saveAll(users);
        final Optional<EBikeImpl> fileUser = this.eBikeRepository.getObjectByID("miao2");
        assertTrue(fileUser.isPresent());
        assertEquals(fileUser.get(), ebike2);
    }

}
