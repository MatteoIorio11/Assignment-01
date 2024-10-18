package sap.ass01.layered.persistence.repositories;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.AbstractRepository;
import sap.ass01.layered.persistence.Serializer;

public class EBikeRepository extends AbstractRepository<EBikeImpl, String> {
    public EBikeRepository(final Serializer<EBikeImpl, String> serializer) {
        super(serializer);
    }

}
