package sap.ass01.layered.persistence.json.repositories;

import sap.ass01.layered.business.Ride;
import sap.ass01.layered.persistence.AbstractRepository;
import sap.ass01.layered.persistence.json.Serializer;

public class RideRepository extends AbstractRepository<Ride, String> {
    public RideRepository(Serializer<Ride, String> serializer) {
        super(serializer);
    }
}
