package com.podcrash.game.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;

import java.util.HashMap;
import java.util.Map;

public class GameScoreboard {

    private final Objective objective;

    private Map<Integer, ScoreboardObject> scoreboardObjects;

    public GameScoreboard(String name) {
        this.objective = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective(name, "dummy");
        this.scoreboardObjects = new HashMap<>();
    }

    public void setTitle(String title) {
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
    }

    public void setLine(int i, String text) {
        objective.getScore(text).setScore(i);
    }

    public void setLine(int i, ScoreboardObject scoreboardObject) {
        //
    }
}
