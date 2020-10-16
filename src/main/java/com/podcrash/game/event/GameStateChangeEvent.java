package com.podcrash.game.event;

import com.podcrash.game.Game;
import com.podcrash.game.GameState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class GameStateChangeEvent extends GameEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final GameState oldState;
    private final GameState newState;

    private boolean cancelled;

    public GameStateChangeEvent(Game game, GameState oldState, GameState newState) {
        super(game);
        this.oldState = oldState;
        this.newState = newState;
        this.cancelled = false;
    }

    public GameState getOldState() {
        return oldState;
    }

    public GameState getNewState() {
        return newState;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
