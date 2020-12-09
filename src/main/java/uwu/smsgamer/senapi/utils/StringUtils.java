package uwu.smsgamer.senapi.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import uwu.smsgamer.senapi.SenAPI;

import java.util.*;

public class StringUtils {
    private final SenAPI api;

    private final List<String> d;
    private final HashMap<String, Character> detection = new HashMap<>();

    public StringUtils(SenAPI api) {
        this.api = api;
        detection.put("**", 'o');
        detection.put("__", 'n');
        detection.put("--", 'm');
        detection.put("##", 'l');
        d = new ArrayList<>(detection.keySet());
    }

    // Might be shit idk
    public String markdownToMinecraft(final String input) {
        return markdownToMinecraft(input, 'r');
    }

    public String markdownToMinecraft(final String input, final char reset) {
        final String first = "\u00A7";
        final String after = "\u00A7" + reset;
        StringBuilder output = new StringBuilder(input);
        List<String> checks = new ArrayList<>(d);
        int add = 0;
        boolean skip = false;
        for (int i = 0; i < output.length(); i++) {
            if (skip) {
                skip = false;
                continue;
            }
            if (output.charAt(i) == '\\') {
                add--;
                output.deleteCharAt(i);
                if (output.length() - i > 1)
                    skip = output.charAt(i + 1) == '\\';
                continue;
            }
            List<String> strings = new ArrayList<>(checks);
            strings.addAll(d);
            for (String s : strings) {
                if (input.length() + 1 - i > s.length()) {
                    final String substring = input.substring(i, i + s.length());
                    if (substring.startsWith(s)) {
                        output.delete(i + add, i + s.length() + add);
                        if (checks.size() > 0 && checks.get(0).equals(s)) {
                            output.insert(i + add, after);
                            checks.remove(0);
                        } else {
                            output.insert(i + add, first + detection.get(s));
                            checks.add(0, s);
                        }
                        i += s.length() - 1;
                        add += 2 - s.length();
                        break;
                    }
                }
            }
        }
        return output.toString();
    }

    public String replacePlaceholders(final OfflinePlayer player, final String string) {
        if (api.isPlaceholderAPIEndpoint()) return PlaceholderAPI.setPlaceholders(player, string);
        else return string.replace("%player_name%", player.getName());
    }

    public String colorize(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String colorizeLimited(final String s, char[] onlyAllowed) {
        return colorizeLimited(s, new String(onlyAllowed), 'r');
    }

    public String colorizeLimited(final String s, String onlyAllowed) {
        return colorizeLimited(s, onlyAllowed, 'r');
    }

    public String colorizeLimited(final String s, char[] onlyAllowed, char reset) {
        return colorizeLimited(s, new String(onlyAllowed), reset);
    }

    public String colorizeLimited(final String s, String onlyAllowed, char reset) {
        char[] output = s.toCharArray();
        for (int i = 0; i < output.length; i++) {
            if (output[i] == '\u00A7') output[i] = '&';
            if (output[i] == '&') {
                char c = Character.toLowerCase(s.charAt(i + 1));
                if ("1234567890abcdefklmnor".indexOf(c) != -1) {
                    if (onlyAllowed.indexOf(c) != -1) {
                        output[i] = '\u00A7';
                        if (c == 'r') {
                            output[i + 1] = reset;
                        }
                    }
                }
            }
        }
        return String.valueOf(output);
    }

    public String replaceArgsPlaceholders(final String string, final String[] args) {
        StringBuilder sb = new StringBuilder();
        int at = 0;
        while (string.length() > at) {
            int current = at;
            boolean append = true;
            if (string.charAt(at) == '%') {
                int i = 0;
                while (string.charAt(++at) >= '0' && string.charAt(at) <= '9') i += string.charAt(at) - '0';
                switch (string.charAt(at)) {
                    case '-':
                        int j = 0;
                        while (Character.isDigit(string.charAt(++at))) j += string.charAt(at) - '0';
                        j = Math.min(j, args.length);
                        if (string.charAt(at) == '%') {
                            append = false;
                            for (; i < j; i++) sb.append(args[i]).append(i == j - 1 ? "" : " ");
                        }
                        break;
                    case '+':
                        if (string.charAt(++at) == '%') {
                            append = false;
                            for (; i < args.length; i++) sb.append(args[i]).append(i == args.length - 1 ? "" : " ");
                        }
                        break;
                    case '%':
                        append = false;
                        if (i < args.length) sb.append(args[i]);
                }
            }
            if (append) sb.append(string, current, at + 1);
            at++;
        }
        return sb.toString();
    }
}
