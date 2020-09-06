package com.podcrash.gamecore.game;

import com.podcrash.gamecore.utils.MathUtil;
import org.bukkit.entity.Player;

import java.util.List;

public class GameTeam {
    private GameSide side;
    private List<Player> players;

    public GameTeam(List<Player> players) {
        this.players = players;
    }

    public void setSide(GameSide side) {
        this.side = side;
    }

    public GameSide getSide() {
        return side;
    }

    public Player randomPlayer() {
        return players.get(MathUtil.randomInt(players.size()));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public int size() {
        return players.size();
    }

}
