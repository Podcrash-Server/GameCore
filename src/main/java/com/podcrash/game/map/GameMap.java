package com.podcrash.game.map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class GameMap {

    private final String name;
    private final String game;

    public GameMap(String name, String game) {
        this.name = name;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public World loadWorld() {
        return Bukkit.createWorld(new WorldCreator(name));
    }

    public void unloadWorld() {
        Bukkit.unloadWorld(name, false);
    }
}
