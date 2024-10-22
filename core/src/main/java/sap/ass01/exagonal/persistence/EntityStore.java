package sap.ass01.exagonal.persistence;

import java.util.Optional;

public interface EntityStore<T, K> {
    void serialize(T object);
    void serializeAll(Iterable<T> objects);
    void update(T object);
    Iterable<T> readAll();
    Optional<T> readByID(K key);

}
