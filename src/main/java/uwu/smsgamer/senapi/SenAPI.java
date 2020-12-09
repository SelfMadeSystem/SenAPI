package uwu.smsgamer.senapi;

import uwu.smsgamer.senapi.utils.*;

public interface SenAPI {
    /**
     * Returns if PlaceholderAPI is enabled.
     *
     * @return if PlaceholderAPI is enabled.
     */
    boolean isPlaceholderAPIEnabled();

    /**
     * Returns the {@link StringUtils}.
     *
     * @return the {@link StringUtils}.
     */
    StringUtils getStringUtils();

    /**
     * Returns the {@link StringUtils}.
     *
     * @return the {@link StringUtils}.
     */
    PlayerUtils getPlayerUtils();
}
