package com.podcrash.gamecore.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Game implements IGame {

    private final String name;
    private final int minPlayers;
    private final int maxPlayers;
    private final int hardMaxPlayers;

    public Game(String name, int minPlayers, int maxPlayers, int hardMaxPlayers) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.hardMaxPlayers = hardMaxPlayers;
    }
}
