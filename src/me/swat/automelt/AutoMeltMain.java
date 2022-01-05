package me.swat.automelt;

import org.bukkit.plugin.java.JavaPlugin;

public class AutoMeltMain extends JavaPlugin {

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new AutoMeltListener(this),this);
    }

}
