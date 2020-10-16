package com.podcrash.game.event;

import com.podcrash.game.Game;
import org.bukkit.event.Event;

public abstract class GameEvent extends Event {

    protected Game game;

    public GameEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
