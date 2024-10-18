package sap.ass01.layered.persistence.repositories;

import sap.ass01.layered.business.Ride;
import sap.ass01.layered.persistence.AbstractRepository;
import sap.ass01.layered.persistence.Serializer;

public class RideRepository extends AbstractRepository<Ride, String> {
    public RideRepository(Serializer<Ride, String> serializer) {
        super(serializer);
    }
}
