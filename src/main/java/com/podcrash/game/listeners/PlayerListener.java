package com.podcrash.game.listeners;

import com.podcrash.game.scoreboard.LobbyScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.LocalDate;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(playerJoinEvent.getPlayer());
        lobbyScoreboard.setPlayerCount(1, 10);
        lobbyScoreboard.setGame("Minestrike");
        lobbyScoreboard.setTeam("SWAT", ChatColor.BLUE);
        lobbyScoreboard.setMap("Cache");
        lobbyScoreboard.setKit("N/A");
        lobbyScoreboard.setDate(LocalDate.now());
        lobbyScoreboard.display();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        playerQuitEvent.getPlayer().getScoreboard().getObjective(playerQuitEvent.getPlayer().getName()).unregister();
    }
}
