package sap.ass01.layered.persistence.repositories;

import sap.ass01.layered.business.Ride;
import sap.ass01.layered.persistence.AbstractRepository;
import sap.ass01.layered.persistence.json.Serializer;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RideRepository extends AbstractRepository<Ride, String> {
    public RideRepository(Serializer<Ride, String> serializer) {
        super(serializer);
    }

    @Override
    public String generateNewId() {
        final List<String> ids = new LinkedList<>();
        super.getAll().iterator().forEachRemaining(ride -> ids.add(ride.getId()));
        return ids.stream().max(String::compareTo).map(id -> new BigInteger(id).add(BigInteger.ONE)).orElse(BigInteger.ZERO).toString();
    }
}
