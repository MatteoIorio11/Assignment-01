package sap.ass01.exagonal.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginClassLoader extends ClassLoader {
    private final JarFile jarFile;

    public PluginClassLoader(final String jarFilePath) throws Exception {
        this.jarFile = new JarFile(jarFilePath);
    }

    @Override
    protected Class<?> findClass(final String className) throws ClassNotFoundException {
        // Convert the class name to the expected path in the JAR
        final String classFile = className.replace('.', '/') + ".class";
        final JarEntry entry = jarFile.getJarEntry(classFile);

        if (entry == null) {
            throw new ClassNotFoundException(className);
        }

        try (final InputStream input = jarFile.getInputStream(entry)) {
            // Read the class bytes
            final byte[] classData = input.readAllBytes();
            // Define the class using the byte array
            return defineClass(className, classData, 0, classData.length);
        } catch (final IOException e) {
            throw new ClassNotFoundException(className, e);
        }
    }
}
