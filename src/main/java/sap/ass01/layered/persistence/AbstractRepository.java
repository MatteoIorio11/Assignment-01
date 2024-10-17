package sap.ass01.layered.persistence;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractRepository<T, K> implements Repository<T, K> {
    private final Serializer<T, K> serializer;
    public AbstractRepository(final Serializer<T, K> serializer){
        this.serializer = serializer;
    }
    @Override
    public void saveAll(Iterable<T> objects) {
        this.serializer.serializeAll(objects);
    }

    @Override
    public void save(T object) {
        this.serializer.serialize(object);
    }

    @Override
    public Iterable<T> getAll() {
        return this.serializer.readAll();
    }

    @Override
    public Optional<T> getObjectByID(K objectKey) {
        return this.serializer.readByID(objectKey);
    }
}
