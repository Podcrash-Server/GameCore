package com.podcrash.gamecore;

import com.podcrash.gamecore.game.Game;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameManager<T extends Game> {

    private Game game;
    private GameState gameState;

    private int softMaxPlayers;
    private int hardMaxPlayers;

    private final List<Player> spectators;

    public GameManager(Class<T> tClass) {
        try {
            this.game = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.spectators = new ArrayList<>();
    }

    public void startGame() {
        game.start();
    }

    public void stopGame() {
        game.stop();
    }

    public void setUp() {
    }

    public void cleanUp() {
        //
    }
}
