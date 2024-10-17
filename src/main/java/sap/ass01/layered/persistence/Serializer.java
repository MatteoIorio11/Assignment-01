package sap.ass01.layered.persistence;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Serializer<T, K> {
    void serialize(T object);
    void serializeAll(Iterable<T> objects);
    Iterable<T> readAll();
    Optional<T> readByID(K key);

    static <T> List<T> iterableToList(Iterable<T> iterable) {
        final List<T> list = new LinkedList<>();
        for (final T item : iterable) {
            list.add(item);
        }
        return list;
    }
}
