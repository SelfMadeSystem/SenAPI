package uwu.smsgamer.senapi;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.*;
import java.util.logging.Logger;

public class SpigotLoader extends Loader {
    private static Method getFileMethod;

    static {
        try {
            getFileMethod = JavaPlugin.class.getDeclaredMethod("getFile");
            getFileMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public JavaPlugin plugin;

    public SpigotLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public File getDataFolder() {
        return plugin.getDataFolder();
    }

    @Override
    public File getFile() {
        try {
            return (File) getFileMethod.invoke(plugin);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Logger getLogger() {
        return plugin.getLogger();
    }
}
