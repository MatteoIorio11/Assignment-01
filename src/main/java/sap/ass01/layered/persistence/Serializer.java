package sap.ass01.layered.persistence;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.json.JacksonSerializer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Serializer<T, K> {
    void serialize(T object);
    void serializeAll(Iterable<T> objects);
    Iterable<T> readAll();
    Optional<T> readByID(K key);

    static Serializer<User, String> userJSONSerializer() {
        final SimpleModule module = new SimpleModule("UserModule", Version.unknownVersion());
        final SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(User.class, UserImpl.class);
        module.setAbstractTypes(resolver);
        return new JacksonSerializer<>(User.class, module);
    }

    static Serializer<EBike, String> ebikeJSONSerializer() {
        final SimpleModule module = new SimpleModule("EBikeModule", Version.unknownVersion());
        final SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(EBike.class, EBikeImpl.class);
        module.setAbstractTypes(resolver);
        return new JacksonSerializer<>(EBike.class, module);
    }

    static <T> List<T> iterableToList(Iterable<T> iterable) {
        final List<T> list = new LinkedList<>();
        for (final T item : iterable) {
            list.add(item);
        }
        return list;
    }
}
