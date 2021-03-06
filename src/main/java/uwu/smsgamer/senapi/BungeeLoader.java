package uwu.smsgamer.senapi;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.util.logging.Logger;

/**
 * {@link Loader} implementation for BungeeCord.
 */
public class BungeeLoader extends Loader {
    public Plugin plugin;

    public BungeeLoader(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public File getDataFolder() {
        return plugin.getDataFolder();
    }

    @Override
    public File getFile() {
        return plugin.getFile();
    }

    @Override
    public Logger getLogger() {
        return plugin.getLogger();
    }
}
