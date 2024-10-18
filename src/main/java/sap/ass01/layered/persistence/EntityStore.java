package sap.ass01.layered.persistence;

import java.util.Optional;

public interface EntityStore<T, K> {
    void serialize(T object);
    void serializeAll(Iterable<T> objects);
    Iterable<T> readAll();
    Optional<T> readByID(K key);

}
