package sap.ass01.layered.persistence;

import java.io.Serializable;
import java.util.Optional;

public interface Repository<T extends SerializableEntity<?>, K>{
    // TODO: CRUD operations
    void saveAll(Iterable<T> objects);
    void save(T object);
    Iterable<T> getAll();
    Optional<T> getObject(K objectKey);
}
