package com.podcrash.gamecore;

import lombok.Getter;

@Getter
public class Game {

    private final String game;
    private final int minPlayers;
    private final int maxPlayers;
    private final int hardMaxPlayers;

    public Game(String game, int minPlayers, int maxPlayers, int hardMaxPlayers) {
        this.game = game;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.hardMaxPlayers = hardMaxPlayers;
    }

    public void start() {
        //
    }

    public void stop() {
        //
    }
}
