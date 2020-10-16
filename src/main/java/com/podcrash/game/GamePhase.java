package com.podcrash.game;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class GamePhase implements IGamePhase, Listener {

    private final String name;

    private boolean active;

    protected GamePhase(String name) {
        this.name = name;
        this.active = false;
        Bukkit.getServer().getPluginManager().registerEvents(this, Game.getInstance().getPlugin());
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
