package sap.ass01.exagonal.services;

import sap.ass01.exagonal.business.RidePlugin;
import sap.ass01.exagonal.plugin.PluginClassLoader;

import java.io.File;

public class PluginService {
    private final ServiceProvider serviceProvider;

    public PluginService(final ServiceProvider serviceProvider) {
       this.serviceProvider = serviceProvider ;
    }

    public boolean loadRidePlugin(final File file) {
        try {
            final PluginClassLoader loader = new PluginClassLoader(file.getAbsolutePath());
            final String className = "sap.ass01.exagonal.plugin." + file.getName().replaceFirst(".jar", "");
            final Class<?> pluginClass = loader.loadClass(className);
            final RidePlugin effectPlugin = (RidePlugin) pluginClass.getDeclaredConstructor(null).newInstance(null);
            this.serviceProvider.getRideService().registerPlugin(effectPlugin, file.getName().replaceFirst(".jar", ""));
        } catch (final Exception ignored) {
            return false;
        }
        return true;
    }
}
