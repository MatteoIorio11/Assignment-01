package sap.ass01.layered.persistence.repositories;

import sap.ass01.layered.business.User;
import sap.ass01.layered.persistence.AbstractRepository;
import sap.ass01.layered.persistence.json.Serializer;


public class UserRepository extends AbstractRepository<User, String> {
    public UserRepository(Serializer<User, String> serializer) {
        super(serializer);
    }
}
