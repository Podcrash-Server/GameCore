package com.podcrash.game.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

public class GameTeam {

    private final String name;
    private final ChatColor chatColor;
    private final Team team;

    /**
     * Create new team for game
     * @param name Name of team
     * @param chatColor Color code
     */
    public GameTeam(String name, ChatColor chatColor) {
        this.name = name;
        this.chatColor = chatColor;
        this.team = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam(name);
        team.setPrefix(chatColor.toString());
    }

    public String getName() {
        return name;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public Team getScoreboardTeam() {
        return team;
    }
}
