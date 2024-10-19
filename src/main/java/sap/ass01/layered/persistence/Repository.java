package sap.ass01.layered.persistence;

import java.io.Serializable;
import java.util.Optional;

public interface Repository<T, K>{
    void saveAll(Iterable<T> objects);
    void save(T object);

    default void update(T object) {
        this.save(object);
    };

    Iterable<T> getAll();
    Optional<T> getObjectByID(K objectKey);
    K generateNewId();
}
