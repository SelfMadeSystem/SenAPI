package uwu.smsgamer.senapi;

import java.io.File;
import java.util.logging.Logger;

/**
 * Loader class for the {@link uwu.smsgamer.senapi.utils.FileUtils}
 */
public abstract class Loader {
    /**
     * You should set this on load.
     */
    public static Loader loader;

    public abstract File getDataFolder();

    public abstract File getFile();

    public abstract Logger getLogger();
}
