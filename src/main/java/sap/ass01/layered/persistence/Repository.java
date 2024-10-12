package sap.ass01.layered.persistence;

import java.io.Serializable;

public interface Repository<T extends SerializableEntity<?>, I> {
    // TODO: CRUD operations
    void saveAll(Iterable<T> objects);
    void save(T object);
    void getById(I id);
}
