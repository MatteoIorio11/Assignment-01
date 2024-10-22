package it.unibo.sap.persistence.jackson;

import org.junit.jupiter.api.Test;
import sap.ass01.exagonal.business.EBikeImpl;
import sap.ass01.exagonal.business.Ride;
import sap.ass01.exagonal.business.RideImpl;
import sap.ass01.exagonal.business.UserImpl;
import sap.ass01.exagonal.persistence.json.Serializer;
import sap.ass01.exagonal.persistence.repositories.RideRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RideSerialiseTest {
    final RideRepository rideRepository = new RideRepository(Serializer.rideJSONSerializer());

    @Test
    public void test() {
        final EBikeImpl eBike = new EBikeImpl("miao");
        final UserImpl user = new UserImpl("miao");
        final RideImpl ride = new RideImpl("id", user, eBike);
        rideRepository.save(ride);
        final Optional<Ride> opt = rideRepository.getObjectByID("id");
        assertTrue(opt.isPresent());
    }
}
