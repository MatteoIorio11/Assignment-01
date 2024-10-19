package it.unibo.sap.persistence.jackson;

import org.junit.jupiter.api.Test;
import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.persistence.json.Serializer;
import sap.ass01.layered.persistence.repositories.EBikeRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EBikeSerializeTest {
    private final EBikeRepository eBikeRepository = new EBikeRepository(Serializer.ebikeJSONSerializer());

    @Test
    public void testSerialiseUser() {
        final EBike ebike = new EBikeImpl("miao");
        this.eBikeRepository.save(ebike);
        final Optional<EBike> fileUser = this.eBikeRepository.getObjectByID("miao");
        assertTrue(fileUser.isPresent());
        assertEquals(fileUser.get(), ebike);
    }

    @Test
    public void testSerialiseUsers() {
        final EBike ebike1 = new EBikeImpl("miao1");
        final EBike ebike2 = new EBikeImpl("miao2");
        final EBike ebike3 = new EBikeImpl("miao3");

        final List<EBike> users = List.of(
                ebike1,
                ebike2,
                ebike3
        );
        this.eBikeRepository.saveAll(users);
        final Optional<EBike> fileUser = this.eBikeRepository.getObjectByID("miao2");
        assertTrue(fileUser.isPresent());
        assertEquals(fileUser.get(), ebike2);
    }

}
