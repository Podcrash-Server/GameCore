package com.podcrash.gamecore.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Game implements IGame {

    private final String name;
    private final int minPlayers;
    private final int maxPlayers;
    private final int hardMaxPlayers;
    private final List<GameTeam> teams;

    public Game(String name, int minPlayers, int maxPlayers, int hardMaxPlayers) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.hardMaxPlayers = hardMaxPlayers;
        this.teams = new ArrayList<>();
    }

    public void addTeam(GameTeam team) {
        this.teams.add(team);
    }

    public boolean isFull() {
        return Bukkit.getOnlinePlayers().size() >= getMaxPlayers();
    }

}
