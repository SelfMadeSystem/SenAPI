package uwu.smsgamer.senapi.config;

import lombok.Getter;

/**
 * Object to store and get configuration values with little
 * to no performance (all gotten on start and reload).
 * Also dynamic so developers can add as much as they want
 * and they automatically get added to the config files.
 *
 * @author Sms_Gamer_3808 (Shoghi Simon)
 */
@Getter
public class ConfVal<T> {
    public T value;
    public T dVal;
    public final String name;
    public final String config;

    /**
     * Constructor for {@link ConfVal}. Default config is {@code config}
     *
     * @param name The path of this value.
     * @param defaultVal The default value.
     */
    public ConfVal(String name, T defaultVal) {
        this(name, "config", defaultVal);
    }

    /**
     * Constructor for {@link ConfVal}.
     *
     * @param name The path of this value.
     * @param config The name of the config that this value lies in.
     * @param defaultVal The default value.
     */
    public ConfVal(String name, String config, T defaultVal) {
        this.name = name;
        this.config = config;
        this.dVal = defaultVal;
        ConfigManager.getInstance().setConfVal(this, defaultVal);
    }

    /**
     * Sets the value of this ConfVal.<br>
     * <strong>Make sure to save the file afterwards since this doesn't save the file.</strong>
     *
     * @param val The value to set it to.
     */
    public void setValue(T val) {
        value = val;
        ConfigManager.getInstance().getConfig(config).set(name, value);
    }

    /**
     * Gets the value or the default if the value is null.
     *
     * @return the value or the default if the value is null.
     */
    public T getValue() {
        if (value == null) return dVal;
        else return value;
    }

    /**
     * Gets the value as a {@code byte}.
     *
     * @return the value as a {@code byte}.
     */
    public byte getByte() {
        return ((Number) value).byteValue();
    }

    /**
     * Gets the value as a {@code short}.
     *
     * @return the value as a {@code short}.
     */
    public short getShort() {
        return ((Number) value).shortValue();
    }

    /**
     * Gets the value as a {@code int}.
     *
     * @return the value as a {@code int}.
     */
    public int getInt() {
        return ((Number) value).intValue();
    }

    /**
     * Gets the value as a {@code long}.
     *
     * @return the value as a {@code long}.
     */
    public long getLong() {
        return ((Number) value).longValue();
    }

    /**
     * Gets the value as a {@code float}.
     *
     * @return the value as a {@code float}.
     */
    public float getFloat() {
        return ((Number) value).floatValue();
    }

    /**
     * Gets the value as a {@code double}.
     *
     * @return the value as a {@code double}.
     */
    public double getDouble() {
        return ((Number) value).doubleValue();
    }
}
