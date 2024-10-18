package sap.ass01.layered.persistence.json.repositories;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.persistence.AbstractRepository;
import sap.ass01.layered.persistence.json.Serializer;

public class EBikeRepository extends AbstractRepository<EBike, String> {
    public EBikeRepository(final Serializer<EBike, String> serializer) {
        super(serializer);
    }
}
