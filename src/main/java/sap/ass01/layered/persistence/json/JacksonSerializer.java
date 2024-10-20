package sap.ass01.layered.persistence.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import sap.ass01.layered.persistence.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JacksonSerializer<T, K> implements Serializer<T, K>{
    private final File file;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> typeClass;
    private static final String RESOURCE_PATH = "src/main/resources/json/";

    public JacksonSerializer(final Class<T> clazz) {
        this.objectMapper.registerModule(new Jdk8Module());
        this.file = new File( RESOURCE_PATH + clazz.getSimpleName().toLowerCase() + ".json");
        this.typeClass = clazz;
        this.checkFile();
    }
    public JacksonSerializer(final Class<T> clazz, final SimpleModule module) {
        this(clazz);
        this.objectMapper.registerModule(module);
    }

    @Override
    public void serialize(T object) {
        final List<T> myObjects = Serializer.iterableToList(this.readAll());
        final int index = myObjects.indexOf(object);
        // Replace the object if exists
        if (index >= 0){
           myObjects.set(index, object);
        }else {
            myObjects.add(object);
        }
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
    public void update(T object) {
        this.serialize(object);
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
                final Method[] methods = obj.getClass().getMethods();
                for (final Method method : methods) {
                    if (method.isAnnotationPresent(Key.class)) {
                        final Object objectKey = method.invoke(obj);
                        if (Objects.nonNull(objectKey) && objectKey.equals(key)) {
                            return Optional.of(obj);
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return Optional.empty();
    }

    private void checkFile() {
        this.checkDirectory();
        if (!Files.exists(Paths.get(this.file.getPath()))) {
            try {
                Files.createFile(Paths.get(this.file.getPath()));
            }catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private void checkDirectory() {
        final Path path = Paths.get(JacksonSerializer.RESOURCE_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            }catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }
}
