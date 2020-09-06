package com.podcrash.gamecore.scoreboard;

import com.podcrash.gamecore.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;

public abstract class ScoreboardLine<T extends Game> {

    private Team team;
    private Score score;
    private String name;
    private GameScoreboard<T> scoreboard;

    public ScoreboardLine(GameScoreboard<T> scoreboard, String line, int score) {
        String prev = ChatColor.values()[score-1] + "§r";
        team = scoreboard.getScoreboard().registerNewTeam(prev);
        (this.score = scoreboard.getStatus().getObjective().getScore(prev)).setScore(score);
        team.addEntry(prev);
        this.scoreboard = scoreboard;
        update(line);
    }

    public void unregister() {
        if(team == null) {
            return;
        }
        team.unregister();
        team = null;
    }

    public Score getScore() {
        return score;
    }

    public abstract void update(String line);
}
