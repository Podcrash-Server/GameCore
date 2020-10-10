package com.podcrash.game.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LobbyScoreboard {

    private final Player player;
    private final GameScoreboard gameScoreboard;

    public LobbyScoreboard(Player player) {
        this.player = player;
        this.gameScoreboard = new GameScoreboard(player.getName(), 15);
        gameScoreboard.setLine(15, ChatColor.AQUA + player.getName());
        gameScoreboard.setLine(13, ChatColor.translateAlternateColorCodes('&', "&b&lPlayers"));
        gameScoreboard.setLine(10, ChatColor.translateAlternateColorCodes('&', "&b&lTeam"));
        gameScoreboard.setLine(7, ChatColor.translateAlternateColorCodes('&', "&b&lMap"));
        gameScoreboard.setLine(4, ChatColor.translateAlternateColorCodes('&', "&b&lKit"));
        gameScoreboard.setPlayer(player);
    }

    public void setGame(String game) {
        gameScoreboard.setTitle(ChatColor.YELLOW + "" + ChatColor.BOLD + game);
    }

    public void setPlayerCount(int playerCount, int max) {
        gameScoreboard.setLine(12, ChatColor.YELLOW + "" + playerCount + "/" + max);
    }

    public void setTeam(String teamName, ChatColor chatColor) {
        gameScoreboard.setLine(9, chatColor + teamName);
    }

    public void setMap(String map) {
        gameScoreboard.setLine(6, ChatColor.YELLOW + map);
    }

    public void setKit(String kit) {
        gameScoreboard.setLine(3, ChatColor.YELLOW + kit);
    }

    public void setDate(LocalDate localDate) {
        gameScoreboard.setLine(1, ChatColor.AQUA + localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public void display() {
        gameScoreboard.display();
    }
}
