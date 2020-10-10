package com.podcrash.game;

import com.podcrash.game.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GameCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
