package sap.ass01.layered.persistence;

import java.io.Serializable;

public interface Repository {
    // TODO: CRUD operations
    void saveAll(Iterable<SerializableEntity> objects);
}
