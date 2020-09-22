package com.podcrash.gamecore;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.podcrash.gamecore.commands.TestLeaderboardCommand;
import com.podcrash.gamecore.commands.TestTitleCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class GameCore extends JavaPlugin {

    private static TaskChainFactory taskChainFactory;
    private static GameCore INSTANCE;
    private static String kitPrefix = String.format("%sKit>%s", ChatColor.BLUE, ChatColor.GRAY);

    @Override
    public void onEnable() {
        INSTANCE = this;

        taskChainFactory = BukkitTaskChainFactory.create(this);
        getCommand("lbtest").setExecutor(new TestLeaderboardCommand());
        getCommand("titletest").setExecutor(new TestTitleCommand());
    }

    @Override
    public void onDisable() {
        //
    }

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public static <T> TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }

    public static GameCore getInstance() {
        //Not adding npe check because you'll never get an instance of the plugin before the plugin exists.
        return INSTANCE;
    }

    private static String getKitPrefix() {
        return kitPrefix;
    }
}
