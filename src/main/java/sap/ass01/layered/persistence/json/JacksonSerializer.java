package sap.ass01.layered.persistence.json;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sap.ass01.layered.business.User;
import sap.ass01.layered.business.UserImpl;
import sap.ass01.layered.persistence.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

public class JacksonSerializer<T, K> implements Serializer<T, K>{
    private final File file;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> typeClass;

    public JacksonSerializer(final Class<T> clazz) {
        this.file = new File(Serializer.path() + clazz.getSimpleName().toLowerCase() + ".json");
        this.typeClass = clazz;
        this.checkFile();
    }

    @Override
    public void serialize(T object) {
        final List<T> myObjects = Serializer.iterableToList(this.readAll());
        System.out.println(myObjects);
        myObjects.add(object);
        System.out.println(myObjects);
        this.serializeAll(myObjects);
    }

    @Override
    public void serializeAll(Iterable<T> objects) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(this.file, objects);
        }catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public Iterable<T> readAll() {
        try {
            return objectMapper.readValue(this.file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, this.typeClass));
        }catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
        return new LinkedList<T>();
    }

    @Override
    public Optional<T> readByID(K key) {
        final Iterable<T> output = this.readAll();
        for (final T obj : output) {
            try {
                System.out.println(obj.getClass());
                final Method[] methods = obj.getClass().getDeclaredMethods();
                for (final Method method : methods) {
                    if (method.isAnnotationPresent(Key.class)) {
                        final Object objectKey = method.invoke(obj);
                        if (Objects.nonNull(objectKey) && objectKey.equals(key)) {
                            return Optional.of(obj);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private void checkFile() {
        if (!Files.exists(Paths.get(this.file.getPath()))) {
            try {
                Files.createFile(Paths.get(this.file.getPath()));
            }catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }
}
