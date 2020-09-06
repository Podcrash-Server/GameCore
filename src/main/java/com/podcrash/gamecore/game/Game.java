package com.podcrash.gamecore.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Game implements IGame {

    private final String name;
    private final int startingPlayers;
    private final int maxPlayers;

    protected Game(String name, int startingPlayers, int maxPlayers) {
        this.name = name;
        this.startingPlayers = startingPlayers;
        this.maxPlayers = maxPlayers;
    }
}
