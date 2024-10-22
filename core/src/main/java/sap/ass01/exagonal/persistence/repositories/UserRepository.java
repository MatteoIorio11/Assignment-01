package sap.ass01.exagonal.persistence.repositories;

import sap.ass01.exagonal.business.User;
import sap.ass01.exagonal.persistence.AbstractRepository;
import sap.ass01.exagonal.persistence.json.Serializer;


public class UserRepository extends AbstractRepository<User, String> {
    public UserRepository(Serializer<User, String> serializer) {
        super(serializer);
    }
}
