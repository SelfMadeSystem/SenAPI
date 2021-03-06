package uwu.smsgamer.senapi;

import java.io.File;
import java.util.logging.Logger;

/**
 * Your plugin should extend this.
 */
public abstract class Loader {
    public static Loader loader;

    public abstract File getDataFolder();
    public abstract File getFile();
    public abstract Logger getLogger();
}
