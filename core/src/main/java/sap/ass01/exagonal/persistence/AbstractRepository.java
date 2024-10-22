package sap.ass01.exagonal.persistence;

import java.util.Optional;

public abstract class AbstractRepository<T, K> implements Repository<T, K> {
    private final EntityStore<T, K> serializer;
    public AbstractRepository(final EntityStore<T, K> serializer){
        this.serializer = serializer;
    }
    @Override
    public synchronized void saveAll(Iterable<T> objects) {
        this.serializer.serializeAll(objects);
    }

    @Override
    public synchronized void save(T object) {
        this.serializer.serialize(object);
    }

    @Override
    public synchronized void update(T object) {
        this.serializer.update(object);
    }

    @Override
    public synchronized Iterable<T> getAll() {
        return this.serializer.readAll();
    }
    @Override
    public synchronized Optional<T> getObjectByID(K objectKey) {
        return this.serializer.readByID(objectKey);
    }

    @Override
    public K generateNewId() {
        throw new UnsupportedOperationException("generateNewID not implemented");
    }
}
