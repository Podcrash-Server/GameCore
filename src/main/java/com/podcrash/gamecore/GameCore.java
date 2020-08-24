package com.podcrash.gamecore;

import com.podcrash.gamecore.commands.TestLeaderboardCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class GameCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("lbtest").setExecutor(new TestLeaderboardCommand());
    }

    @Override
    public void onDisable() {
        //
    }
}
