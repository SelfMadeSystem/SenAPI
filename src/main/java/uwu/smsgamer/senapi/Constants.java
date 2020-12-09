package uwu.smsgamer.senapi;

public class Constants {
    private static final char[] COLORS_CHARS = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'k', 'l', 'm', 'n', 'o', 'r'};
    private static final String COLORS_STRING = "1234567890abcdefklmnor";

    public static char[] getCharColors() {
        char[] c = new char[COLORS_CHARS.length];
        System.arraycopy(COLORS_CHARS, 0, c, 0, COLORS_CHARS.length);
        return c;
    }

    public static String getStringColors() {
        return COLORS_STRING;
    }
}
