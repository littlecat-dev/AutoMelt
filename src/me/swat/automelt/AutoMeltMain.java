package me.swat.automelt;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoMeltMain extends JavaPlugin {
    PluginManager pm = this.getServer().getPluginManager();

    public void onEnable() {
        pm.registerEvents(new AutoMeltListener(this),this);
    }

}
