package com.podcrash.examplegame;

import com.podcrash.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {
        game = new ExampleGame(this);
    }
}
