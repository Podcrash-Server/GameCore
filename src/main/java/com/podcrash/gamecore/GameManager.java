package com.podcrash.gamecore;

import com.podcrash.gamecore.game.Game;
import com.podcrash.gamecore.game.GameStage;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameManager {

    private final Game game;

    private GameState gameState;
    private final List<Player> spectators;

    private GameStage gameStage;

    public GameManager(Game game) {
        this.game = game;
        this.gameState = GameState.LOBBY;
        this.spectators = new ArrayList<>();
    }

    public void start() {
        //
    }

    public void stop() {
        gameState = GameState.ENDED;
        game.stop();
    }

    public void addSpectator(Player player) {
        spectators.add(player);
    }

    public void removeSpectator(Player player) {
        spectators.remove(player);
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
        this.gameStage.execute(this);
    }
}
