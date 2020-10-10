package com.podcrash.game.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameScoreboard {

    private final String name;
    private final int lines;
    private final Objective objective;
    private final Map<Integer, Team> teams;

    /**
     * Creates scoreboard with name
     * @param name Internal name of scoreboard
     * @param lines Number of lines (max 16)
     */
    public GameScoreboard(String name, int lines) {
        this.name = name;
        if (lines > 16 || lines < 1)
            lines = 16;
        this.lines = lines;
        this.objective = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective(name, "dummy");
        this.teams = new HashMap<>();
        for (int i = lines; i > 0; i--) {
            String entry = ChatColor.values()[i].toString();
            objective.getScore(entry).setScore(i);
            Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(UUID.randomUUID().toString().substring(0, 8));
            team.addEntry(entry);
            teams.put(i, team);
        }
    }

    /**
     * Sets title of scoreboard
     * @param title Title of scoreboard (max ?)
     */
    public void setTitle(String title) {
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
    }

    /**
     * Sets the text of line
     * @param i Scoreboard score
     * @param text Text to input (max ~32; yes color codes count as length)
     */
    public void setLine(int i, String text) {
        if (i > lines || i < 1)
            i = lines;
        if (text.length() > 32)
            text = "Text too long";
        Team team = teams.get(i);
        String[] safeSplit = safeSplit(text);
        team.setPrefix(safeSplit[0]);
        team.setSuffix(safeSplit[1]);
    }

    public String getName() {
        return name;
    }

    public void setPlayer(Player player) {
        player.setScoreboard(objective.getScoreboard());
    }

    public void unregister() {
        objective.unregister();
    }

    public void display() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    private String[] safeSplit(String text) {
        String convertedText = ChatColor.translateAlternateColorCodes('&', text);
        String prefix = convertedText.substring(0, Math.min(16, text.length()));
        String suffix = "";
        if (text.length() > 16)
            suffix = ChatColor.getLastColors(prefix) + convertedText.substring(16, text.length());
        if (suffix.length() > 16)
            suffix = suffix.substring(0, 16);
        return new String[]{prefix,suffix};
    }
}
