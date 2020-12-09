package uwu.smsgamer.senapi;

import uwu.smsgamer.senapi.utils.*;

public interface SenAPI {
    boolean isPlaceholderAPIEndpoint();

    StringUtils getStringUtils();

    PlayerUtils getPlayerUtils();
}
