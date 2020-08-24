package com.podcrash.gamecore.leaderboard;

import com.podcrash.gamecore.data.RedisDataSource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leaderboard {

    private final String game;
    private final Jedis jedis;

    public Leaderboard(String game, RedisDataSource redisDataSource) {
        this.game = game;
        this.jedis = redisDataSource.getConnection();
    }

    public void display(LeaderboardType leaderboardType) {
        //
    }

    public Set<LeaderboardEntry> fetchLeaderboardData(LeaderboardType leaderboardType) {
        Set<LeaderboardEntry> leaderboardData = new HashSet<>();
        Set<Tuple> redisData = jedis.zrevrangeWithScores("leaderboard:" + game + ":" + leaderboardType.name().toLowerCase(), 0, 9);
        Pipeline pipeline = jedis.pipelined();
        List<Integer> scores = new ArrayList<>();
        for (Tuple tuple : redisData) {
            String uuid = tuple.getElement();
            pipeline.get("uuid:" + uuid);
            scores.add((int) tuple.getScore());
        }
        List<Object> usernames = pipeline.syncAndReturnAll();
        for (int i = 0; i < scores.size(); i++)
            leaderboardData.add(new LeaderboardEntry(String.valueOf(usernames.get(i)), scores.get(i)));
        return leaderboardData;
    }
}
