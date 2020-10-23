package com.podcrash.game.map;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

public class GameMap {

    private final String name;

    public GameMap(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void loadWorld() {
        Bukkit.createWorld(new WorldCreator(name));
    }

    public void unloadWorld() {
        Bukkit.unloadWorld(name, false);
    }
}
