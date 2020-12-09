package uwu.smsgamer.senapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import uwu.smsgamer.senapi.impl.SenAPIImpl;

public final class SenAPILoader extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServicesManager().register(SenAPI.class, new SenAPIImpl(), this, ServicePriority.Normal);
    }

    @Override
    public void onDisable() {
    }
}
