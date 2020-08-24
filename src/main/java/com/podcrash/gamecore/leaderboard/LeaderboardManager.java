package com.podcrash.gamecore.leaderboard;

import com.podcrash.gamecore.GameModule;
import com.podcrash.gamecore.data.RedisDataSource;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;

import java.util.*;

public class LeaderboardManager extends GameModule {

    private final String game;
    private final Jedis jedis;

    public LeaderboardManager(String game, RedisDataSource redisDataSource) {
        super("leaderboard");
        this.game = game;
        this.jedis = redisDataSource.getConnection();
    }

    public void display(LeaderboardType leaderboardType, Location location) {
        List<LeaderboardEntry> leaderboardData = fetchLeaderboardData(leaderboardType);
        Location gameTitleLocation = location.clone();
        gameTitleLocation.setY(gameTitleLocation.getY() + 2.4);
        spawnArmorStand(
                "&a&l" + game.toUpperCase() + " &f&l- &a&l" + leaderboardType.name(),
                gameTitleLocation);
        for (int i = 0; i < 10; i++) {
            Location armorStandLocation = location.clone();
            armorStandLocation.setY(armorStandLocation.getY() + 2 - 0.4 * i);
            LeaderboardEntry leaderboardEntry;
            try {
                leaderboardEntry = leaderboardData.get(i);
            } catch (IndexOutOfBoundsException e) {
                spawnArmorStand("&a" + (i + 1) + ". &fN/A", armorStandLocation);
                continue;
            }
            spawnArmorStand("&a" + (i + 1) + ". &f" +
                            leaderboardEntry.getUsername() + " &a- &f" +
                            leaderboardEntry.getScore(), armorStandLocation);
        }
    }

    public void resetScores() {
        for (LeaderboardType leaderboardType : LeaderboardType.values()) {
            if (leaderboardType == LeaderboardType.ALL) continue;
            jedis.del("leaderboard:" + game + ":" + leaderboardType.name().toLowerCase());
        }
    }

    public void resetPlayerScore(UUID uuid) {
        for (LeaderboardType leaderboardType : LeaderboardType.values()) {
            if (leaderboardType == LeaderboardType.ALL) continue;
            jedis.zadd("leaderboard:" + game + ":" + leaderboardType.name().toLowerCase(),
                    0,
                    uuid.toString());
        }
    }

    public void incrementPlayerScore(UUID uuid, Integer score) {
        for (LeaderboardType leaderboardType : LeaderboardType.values()) {
            jedis.zincrby("leaderboard:" + game + ":" + leaderboardType.name().toLowerCase(),
                    score,
                    uuid.toString());
        }
    }

    private List<LeaderboardEntry> fetchLeaderboardData(LeaderboardType leaderboardType) {
        List<LeaderboardEntry> leaderboardData = new ArrayList<>();
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

    private void spawnArmorStand(String name, Location location) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
    }
}
