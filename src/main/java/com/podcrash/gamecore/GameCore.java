package com.podcrash.gamecore;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.podcrash.gamecore.commands.TestLeaderboardCommand;
import com.podcrash.gamecore.commands.TestTitleCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class GameCore extends JavaPlugin {

    private static TaskChainFactory taskChainFactory;

    @Override
    public void onEnable() {
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
}
