package uwu.smsgamer.senapi.config;

import de.leonhard.storage.*;
import de.leonhard.storage.internal.settings.ReloadSettings;
import uwu.smsgamer.senapi.Loader;
import uwu.smsgamer.senapi.utils.FileUtils;

import java.io.File;
import java.util.*;

/**
 * Abstract class to store configuration files.
 *
 * @author Sms_Gamer_3808 (Shoghi Simon)
 */
public class ConfigManager {
    public Map<String, Config> configs = new HashMap<>();
    private static ConfigManager instance;

    public static ConfigManager getInstance() {
        if (instance == null) throw new IllegalStateException("ConfigManager.instance is null!");
        return instance;
    }

    public static void setInstance(ConfigManager instance) {
        ConfigManager.instance = instance;
    }

    public void setup(String... configs) {
        if (!Loader.loader.getDataFolder().exists()) {
            Loader.loader.getDataFolder().mkdir();
        }
        for (String config : configs) {
            Loader.loader.getLogger().info("Loading config: " + config);
            try {
                loadConfig(config);
                Loader.loader.getLogger().info("Loaded config: " + config);
            } catch (Exception e) {
                e.printStackTrace();
                Loader.loader.getLogger().severe("Error while loading config: " + config);
            }
        }
    }

    public File configFile(String name) {
        return new File(Loader.loader.getDataFolder(), name + ".yml");
    }

    public void reloadConfig(String name) {
        getConfig(name).forceReload();
    }

    private void loadConfig(String name) {
        if (new Throwable().getStackTrace()[0].getClassName().startsWith("org.python"))
            throw new IllegalStateException("Don't u dare execute this from Python!");

        File configFile = configFile(name);
        if (!configFile.exists())
            FileUtils.saveResource(name + ".yml", Loader.loader.getDataFolder(), false);
        loadConfig(name, configFile);
    }

    public void loadConfig(String name, File file) {
        if (!Loader.loader.getDataFolder().exists()) {
            Loader.loader.getDataFolder().mkdir();
        }
        Config config = LightningBuilder.fromFile(file).createConfig();
        config.setReloadSettings(ReloadSettings.MANUALLY);
        configs.remove(config.getName());
        configs.put(name, config);
    }

    public void saveConfig(String name) {
        Config config = getConfig(name);
        config.write();
    }

    public Config getConfig(String name) {
        return configs.get(name);
    }

    public Set<Map.Entry<String, Config>> getConfigs() {
        return configs.entrySet();
    }

    public Set<ConfVal<?>> vals = new HashSet<>();

    public <T> void reloadConfVal(ConfVal<T> val) {
        setConfVal(val, val.dVal);
    }

    @SuppressWarnings("unchecked")
    public <T> void setConfVal(ConfVal<T> val, T dVal) {
        if (dVal instanceof Map) {
            vals.add(val);
            Config config = configs.get(val.config);
            if (!config.contains(val.name)) {
                config.set(val.name, dVal);
                val.value = dVal;
            } else {
                HashMap<String, Object> map = new HashMap<>();
                for (String key : config.getSection(val.name).singleLayerKeySet())
                    map.put(key, config.get(val.name + "." + key));
                val.value = (T) map;
            }
        } else {
            vals.add(val);
            Config config = configs.get(val.config);
            val.value = config.get(val.name, dVal);
            if (!config.contains(val.name)) config.set(val.name, dVal);
        }
    }
}
