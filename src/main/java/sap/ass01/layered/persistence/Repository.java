package sap.ass01.layered.persistence;

import java.io.Serializable;

public interface Repository<T extends SerializableEntity<?>> {
    // TODO: CRUD operations
    void saveAll(Iterable<T> objects);
}
