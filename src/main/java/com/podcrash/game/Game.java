package com.podcrash.game;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public abstract class Game {

    private static Game instance;

    private final String name;
    private final Logger logger;
    private final JavaPlugin javaPlugin;

    private GamePhase activePhase;

    protected Game(String name, JavaPlugin javaPlugin) {
        instance = this;
        this.name = name;
        this.javaPlugin = javaPlugin;
        this.logger = Bukkit.getLogger();
    }

    public void setPhase(GamePhase gamePhase) {
        logger.info("Ending phase " + activePhase.getName());
        activePhase.end();
        activePhase = gamePhase;
        logger.info("Initializing phase " + activePhase.getName());
        activePhase.init();
        logger.info("Starting phase " + activePhase.getName());
        activePhase.start();
    }

    public GamePhase getActivePhase() {
        return activePhase;
    }

    public JavaPlugin getPlugin() {
        return javaPlugin;
    }

    public static Game getInstance() {
        return instance;
    }
}
