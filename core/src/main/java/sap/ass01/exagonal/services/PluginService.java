package sap.ass01.exagonal.services;

import sap.ass01.exagonal.plugin.RidePlugin;
import sap.ass01.exagonal.plugin.PluginClassLoader;

import java.io.File;

public class PluginService {
    private final ServiceProvider serviceProvider;
    public PluginService(final ServiceProvider serviceProvider) {
       this.serviceProvider = serviceProvider ;
    }
    public void loadRidePlugin(final File file) throws Exception {
        final PluginClassLoader loader = new PluginClassLoader(file.getAbsolutePath());
        System.out.println(file.getName());
        String className = "sap.ass01.plugin.plugins." + file.getName().replaceFirst(".jar","");
        Class<?> pluginClass = loader.loadClass(className);
        RidePlugin effectPlugin = (RidePlugin) pluginClass.getDeclaredConstructor(null).newInstance(null);
        this.serviceProvider.getRideService().registerPlugin(effectPlugin, file.getName().replaceFirst(".jar", ""));
    }
}
