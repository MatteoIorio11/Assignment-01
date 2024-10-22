package sap.ass01.exagonal.persistence.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import sap.ass01.exagonal.business.*;
import sap.ass01.exagonal.persistence.EntityStore;

import java.util.LinkedList;
import java.util.List;

public interface Serializer<T, K> extends EntityStore<T, K> {

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

    static Serializer<Ride, String> rideJSONSerializer() {
        final SimpleModule module = new SimpleModule("RideModule", Version.unknownVersion());
        final SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(User.class, UserImpl.class);
        resolver.addMapping(EBike.class, EBikeImpl.class);
        resolver.addMapping(Ride.class, RideImpl.class);
        module.setAbstractTypes(resolver);
        return new JacksonSerializer<>(Ride.class, module);
    }

    static <T> List<T> iterableToList(Iterable<T> iterable) {
        final List<T> list = new LinkedList<>();
        for (final T item : iterable) {
            list.add(item);
        }
        return list;
    }
}
