package com.podcrash.gamecore.game;

import com.podcrash.gamecore.GameState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Game implements IGame {

    private final String id;
    private final String mapName;
    private final int minPlayers;
    private final int maxPlayers;
    private final int hardMaxPlayers;
    private final Location lobby;
    private GameTeam[] teams;
    private List<Player> spectators;
    private GameStage stage;
    private GameState state;

    public Game(String id, String mapName, int minPlayers, int maxPlayers, int hardMaxPlayers, Location lobby,
                GameTeam[] teams) {
        this.id = id;
        this.mapName = mapName;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.hardMaxPlayers = hardMaxPlayers;
        this.lobby = lobby;
        this.teams = teams;
        this.spectators = new ArrayList<>();
        state = GameState.LOBBY;
    }

    public void addSpectator(Player player) {
        spectators.add(player);
    }

    public void removeSpectator(Player player) {
        spectators.remove(player);
    }

    public void setGameStage(GameStage gameStage) {
        this.stage = gameStage;
        this.stage.execute(this);
    }

}
