package com.podcrash.gamecore.leaderboard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class LeaderboardEntry {

    private final String username;
    private final Integer score;

    public LeaderboardEntry(String username, Integer score) {
        this.username = username;
        this.score = score;
    }
}
