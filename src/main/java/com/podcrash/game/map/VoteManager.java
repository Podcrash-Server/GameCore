package com.podcrash.game.map;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VoteManager {

    private final List<Player> voted;
    private final ConcurrentHashMap<GameMap, Integer> votes;

    public VoteManager() {
        this.voted = new ArrayList<>();
        this.votes = new ConcurrentHashMap<>();
    }

    public void addVote(Player player, GameMap gameMap) {
        voted.add(player);
        int voteCount = votes.get(gameMap);
        votes.put(gameMap, voteCount + 1);
    }

    public boolean alreadyVoted(Player player) {
        return voted.contains(player);
    }

    public GameMap declareWinner() {
        int highestVotes = 0;
        GameMap winner = votes.keySet().stream().iterator().next();
        for (Map.Entry<GameMap, Integer> voteEntry : votes.entrySet()) {
            if (voteEntry.getValue() > highestVotes) {
                highestVotes = voteEntry.getValue();
                winner = voteEntry.getKey();
            }
        }
        return winner;
    }
}
