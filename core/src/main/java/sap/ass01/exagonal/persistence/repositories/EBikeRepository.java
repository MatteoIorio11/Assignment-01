package sap.ass01.exagonal.persistence.repositories;

import sap.ass01.exagonal.business.EBike;
import sap.ass01.exagonal.persistence.AbstractRepository;
import sap.ass01.exagonal.persistence.json.Serializer;

public class EBikeRepository extends AbstractRepository<EBike, String> {
    public EBikeRepository(final Serializer<EBike, String> serializer) {
        super(serializer);
    }
}
