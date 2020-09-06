package com.podcrash.gamecore.scoreboard;

import com.podcrash.gamecore.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public abstract class GameScoreboardTeam<T extends Game> {

    private Scoreboard scoreboard;
    private List<Team> teams;

    public GameScoreboardTeam(T game, Scoreboard scoreboard) {
        teams = new ArrayList<>();
        this.scoreboard = scoreboard;
        registerSides(game);
    }

    public abstract void registerSides(T game);

    public abstract void add(T game, Player player);

    public void remove(Player player) {
        Team team = scoreboard.getTeam(player.getName());
        if(team == null) {
            return;
        }
        teams.remove(team);
        team.unregister();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public abstract void update(T game, Player player);

}
