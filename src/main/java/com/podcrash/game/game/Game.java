package com.podcrash.game.game;

import com.podcrash.game.map.GameMap;
import com.podcrash.game.scoreboard.GameScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Game {

    private final String name;
    private final String version;
    private final int minPlayers;
    private final int maxPlayers;
    private final Map<String, GameMap> maps;
    private final GameScoreboard lobbyScoreboard;

    private final List<Player> actualPlayers;
    private GameMap currentMap;

    /**
     * Game constructor for creating new games
     * @param name Name of the game
     * @param version Version of the game
     * @param minPlayers
     * @param maxPlayers
     */
    protected Game(String name, String version, int minPlayers, int maxPlayers) {
        this.name = name;
        this.version = version;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.maps = new HashMap<>();
        this.lobbyScoreboard = setupLobbyScoreboard();
        this.actualPlayers = new ArrayList<>();
    }

    private GameScoreboard setupLobbyScoreboard() {
        GameScoreboard gameScoreboard = new GameScoreboard("lobby", 16);
        gameScoreboard.setTitle(ChatColor.translateAlternateColorCodes('&', "&e&l" + name + " &b&l" + version));
        gameScoreboard.setLine(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return gameScoreboard;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }

    public List<Player> getActualPlayers() {
        return actualPlayers;
    }

    public void addActualPlayer(Player player) {
        actualPlayers.add(player);
    }

    public void removeActualPlayer(Player player) {
        actualPlayers.remove(player);
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
