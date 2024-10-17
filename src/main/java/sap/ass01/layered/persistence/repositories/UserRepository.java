package sap.ass01.layered.persistence.repositories;

import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.AbstractRepository;
import sap.ass01.layered.persistence.Serializer;
import sap.ass01.layered.persistence.json.JacksonSerializer;


public class UserRepository extends AbstractRepository<UserImpl, String> {
    public UserRepository(Serializer<UserImpl, String> serializer) {
        super(serializer);
    }
}
