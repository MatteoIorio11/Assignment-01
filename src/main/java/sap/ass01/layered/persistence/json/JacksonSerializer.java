package sap.ass01.layered.persistence.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sap.ass01.layered.persistence.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JacksonSerializer<T extends SerializableEntity<?>, K> implements Serializer<T, K> {
    private final Map<Class<?>, File> files:
    private final String destinationPath = "src/main/resources/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JacksonSerializer() {
        this.files = new HashMap<>();

    }
    @Override
    public void serialize(T object) {
        try {
            this.checkFile(object);
            final List<T> myObjects = objectMapper.readValue(this.files.get(object.getClass()), new TypeReference<List<T>>(){});
            myObjects.add(object);
            this.serializeAll(myObjects);
        }catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void serializeAll(Iterable<T> objects) {
        final Optional<T> object = this.getClassFromIterable(objects);
        if (object.isPresent()) {
            try {
                this.checkFile(object.get());
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(this.files.get(object.get().getClass()), objects);
            }catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    @Override
    public Iterable<T> readAll() {
        return null;
    }

    @Override
    public Optional<T> readByID(final K id) {
        return Optional.empty();
    }

    private void checkFile(final T object) {
        final String className = object.getClass().getSimpleName();
        final File file = new File(this.destinationPath + className + ".json");
        if (!Files.exists(Paths.get(file.getPath()))) {
            try {
                Files.createDirectories(Paths.get(file.getPath()));
                this.files.put(object.getClass(), file);
            }catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private Optional<T> getClassFromIterable(final Iterable<T> iterable) {
        for (final T element : iterable) {
            if (element != null) {
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }

}
