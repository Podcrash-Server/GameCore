package com.podcrash.gamecore;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.podcrash.gamecore.commands.TestLeaderboardCommand;
import com.podcrash.gamecore.commands.TestTitleCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GameCore extends JavaPlugin {

    private TaskChainFactory taskChainFactory;
    private static GameCore instance;

    @Override
    public void onEnable() {
        instance = this;
        taskChainFactory = BukkitTaskChainFactory.create(this);
        getCommand("lbtest").setExecutor(new TestLeaderboardCommand());
        getCommand("titletest").setExecutor(new TestTitleCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getScheduler().cancelTasks(this);
    }

    public static GameCore getInstance() {
        return instance;
    }


    public <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public <T> TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }
}
