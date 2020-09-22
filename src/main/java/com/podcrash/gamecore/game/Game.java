package com.podcrash.gamecore.game;

import com.podcrash.gamecore.GameState;
import com.podcrash.gamecore.utils.MathUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Game implements IGame {

    private final String id;
    private final String mapName;
    private final int minPlayers;
    private final int maxPlayers;
    private final int hardMaxPlayers;
    private final Location lobby;
    private GameTeam[] teams;
    private List<Player> spectators;
    private GameStage stage;
    private GameState state;

    public Game(String id, String mapName, int minPlayers, int maxPlayers, int hardMaxPlayers, Location lobby,
                GameTeam[] teams) {
        this.id = id;
        this.mapName = mapName;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.hardMaxPlayers = hardMaxPlayers;
        this.lobby = lobby;
        this.teams = teams;
        this.spectators = new ArrayList<>();
        state = GameState.LOBBY;
    }

    public void addSpectator(Player player) {
        spectators.add(player);
    }

    public void removeSpectator(Player player) {
        spectators.remove(player);
    }

    public void setGameStage(GameStage gameStage) {
        this.stage = gameStage;
        this.stage.execute(this);
    }

    public void clearPlayer(Player player) {
        player.setExp(0);
        player.setLevel(0);
        player.setFireTicks(0);
        player.setFoodLevel(20);
        player.setFlying(false);
        player.setFallDistance(0.0f);
        player.setAllowFlight(false);
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().setArmorContents(null);
        if (player.isInsideVehicle()) {
            player.leaveVehicle();
        }
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
        player.closeInventory();
    }

    public void addPlayerRandomTeam(Player player) {
        int random = MathUtil.randomInt(teams.length);
        teams[random].addPlayer(player);
    }

}
