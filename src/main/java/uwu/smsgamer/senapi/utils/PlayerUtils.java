package uwu.smsgamer.senapi.utils;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permissible;
import uwu.smsgamer.senapi.*;

public class PlayerUtils {
    private final SenAPI api;

    public PlayerUtils(SenAPI api) {
        this.api = api;
    }

    public char[] getAllowedColors(String permissionBase, Permissible base) {
        char[] allowedColors = new char[0];
        for (char c : Constants.getCharColors())
            if (base.hasPermission(permissionBase + c)) {
                char[] chars = new char[allowedColors.length + 1];
                System.arraycopy(allowedColors, 0, chars, 0, allowedColors.length);
                chars[chars.length - 1] = c;
                allowedColors = chars;
            }
        return allowedColors;
    }

    public OfflinePlayer getPlayer(CommandSender sender) {
        if (sender instanceof OfflinePlayer) {
            return (OfflinePlayer) sender;
        } else return ConsolePlayer.getInstance();
    }
}
