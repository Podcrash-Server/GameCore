package com.podcrash.gamecore.scoreboard;

import com.podcrash.gamecore.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.HashMap;
import java.util.Map;

public abstract class ScoreboardStatus<T extends Game> {

    private Player player;
    private Objective objective;
    private GameScoreboard<T> scoreboard;
    private Map<Integer, ScoreboardLine<T>> entries;

    public ScoreboardStatus(Player player,  GameScoreboard<T> scoreboard) {
        this.entries = new HashMap<>();
        this.player = player;
        this.scoreboard = scoreboard;
        (this.objective = scoreboard.getScoreboard().registerNewObjective("status", "dummy")).setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public Player getPlayer() {
        return player;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setTitle(String displayName) {
        objective.setDisplayName(displayName);
    }

    public void reset() {
        for(ScoreboardLine<T> line : entries.values()) {
            line.unregister();
            scoreboard.getScoreboard().resetScores(line.getScore().getEntry());
        }
        entries.clear();
    }

    public abstract void updateLine(int line, String text);

}
