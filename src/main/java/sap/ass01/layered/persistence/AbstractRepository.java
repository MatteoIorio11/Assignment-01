package sap.ass01.layered.persistence;

import sap.ass01.layered.persistence.json.Serializer;

import java.util.Optional;

public abstract class AbstractRepository<T, K> implements Repository<T, K> {
    private final EntityStore<T, K> serializer;
    public AbstractRepository(final EntityStore<T, K> serializer){
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
    public void update(T object) {
        this.save(object);
    }

    @Override
    public Iterable<T> getAll() {
        return this.serializer.readAll();
    }
    @Override
    public Optional<T> getObjectByID(K objectKey) {
        return this.serializer.readByID(objectKey);
    }

    @Override
    public K generateNewId() {
        throw new UnsupportedOperationException("generateNewID not implemented");
    }
}
