package com.podcrash.gamecore.scoreboard;

import com.podcrash.gamecore.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public abstract class GameScoreboard<T extends Game> {

    private Scoreboard scoreboard;
    private GameScoreboardTeam<T> team;
    private ScoreboardStatus<T> status;

    public GameScoreboard(T game, Player player) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(scoreboard);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public GameScoreboardTeam<T> getTeams() {
        return team;
    }

    public ScoreboardStatus<T> getStatus() {
        return status;
    }

    public abstract void showTeams(T game);

    public void removeTeam(){
        if(team == null) {
            return;
        }

        for(Team team : team.getTeams()) {
            team.unregister();
        }
        team.getTeams().clear();
        team = null;
    }

    public void remove() {
        removeTeam();

        for(Objective objective : scoreboard.getObjectives()) {
            objective.unregister();
        }
    }
}
