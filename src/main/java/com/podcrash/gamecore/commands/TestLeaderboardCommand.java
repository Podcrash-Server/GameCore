package com.podcrash.gamecore.commands;

import com.podcrash.gamecore.data.RedisDataSource;
import com.podcrash.gamecore.leaderboard.Leaderboard;
import com.podcrash.gamecore.leaderboard.LeaderboardType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestLeaderboardCommand implements CommandExecutor {

    private final RedisDataSource redisDataSource;

    public TestLeaderboardCommand() {
        this.redisDataSource = new RedisDataSource();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String game = args[0];
        LeaderboardType leaderboardType = LeaderboardType.valueOf(args[1].toUpperCase());
        Leaderboard leaderboard = new Leaderboard(game, redisDataSource);
        leaderboard.display(leaderboardType, ((Player) sender).getLocation());
        return true;
    }
}
