package com.podcrash.game.event;

import com.podcrash.game.Game;
import com.podcrash.game.GamePhase;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class GamePhaseChangeEvent extends GameEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final GamePhase oldPhase;
    private final GamePhase newPhase;

    private boolean cancelled;

    public GamePhaseChangeEvent(Game game, GamePhase oldPhase, GamePhase newPhase) {
        super(game);
        this.oldPhase = oldPhase;
        this.newPhase = newPhase;
        this.cancelled = false;
    }

    public GamePhase getOldPhase() {
        return oldPhase;
    }

    public GamePhase getNewPhase() {
        return newPhase;
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
