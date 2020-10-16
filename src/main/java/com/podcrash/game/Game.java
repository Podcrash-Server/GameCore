package com.podcrash.game;

import com.podcrash.game.event.GamePhaseChangeEvent;
import com.podcrash.game.event.GameStateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public abstract class Game {

    private static Game instance;

    private final String name;
    private final Logger logger;
    private final JavaPlugin javaPlugin;

    private GameState gameState;
    private GamePhase activePhase;

    protected Game(String name, JavaPlugin javaPlugin) {
        instance = this;
        this.name = name;
        this.javaPlugin = javaPlugin;
        this.logger = Bukkit.getLogger();
        this.gameState = GameState.LOBBY;
    }

    public void setState(GameState gameState) {
        GameStateChangeEvent gameStateChangeEvent = new GameStateChangeEvent(this, this.gameState, gameState);
        if (gameStateChangeEvent.isCancelled()) {
            logger.info("State change from " + this.gameState.name() + " to " + gameState.name() + " cancelled by event");
        }
        this.gameState = gameState;
    }

    public void setPhase(GamePhase gamePhase) {
        GamePhaseChangeEvent gamePhaseChangeEvent = new GamePhaseChangeEvent(this, activePhase, gamePhase);
        logger.info("Ending phase " + activePhase.getName());
        Bukkit.getServer().getPluginManager().callEvent(gamePhaseChangeEvent);
        if (gamePhaseChangeEvent.isCancelled()) {
            logger.info("Phase change from " + activePhase.getName() + " to " + gamePhase.getName() + " cancelled by event");
            return;
        }
        activePhase.end();
        activePhase = gamePhase;
        logger.info("Initializing phase " + activePhase.getName());
        activePhase.init();
        logger.info("Starting phase " + activePhase.getName());
        activePhase.start();
    }

    public String getName() {
        return name;
    }

    public GameState getState() {
        return gameState;
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
