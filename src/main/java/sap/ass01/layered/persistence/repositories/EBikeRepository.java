package sap.ass01.layered.persistence.repositories;

import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.persistence.AbstractRepository;
import sap.ass01.layered.persistence.Serializer;

public class EBikeRepository extends AbstractRepository<EBikeImpl, String> {
    public EBikeRepository(Serializer<EBikeImpl, String> serializer) {
        super(serializer);
    }
}
