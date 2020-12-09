package uwu.smsgamer.senapi.impl;

import org.bukkit.Bukkit;
import uwu.smsgamer.senapi.SenAPI;
import uwu.smsgamer.senapi.utils.*;

public class SenAPIImpl implements SenAPI {
    private final StringUtils stringUtils = new StringUtils(this);
    private final PlayerUtils playerUtils = new PlayerUtils(this);
    @Override
    public boolean isPlaceholderAPIEndpoint() {
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    @Override
    public StringUtils getStringUtils() {
        return stringUtils;
    }

    @Override
    public PlayerUtils getPlayerUtils() {
        return playerUtils;
    }
}
