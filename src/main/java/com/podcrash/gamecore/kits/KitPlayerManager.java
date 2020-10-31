package com.podcrash.gamecore.kits;

import com.podcrash.gamecore.GameCore;
import com.podcrash.gamecore.kits.abilitytype.AfterConstruct;
import com.podcrash.gamecore.kits.abilitytype.ChargedAbility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;

public class KitPlayerManager {

    private static JavaPlugin plugin = GameCore.getInstance();
    private static List<KitPlayer> kitPlayers = new ArrayList<>();

    public static void register(KitPlayer player) {
        player.getPlayer().resetMaxHealth();

        //For games where players can select multiple kits.
        if (!player.getUsedKits().isEmpty() && player.getUsedKits().size() > 1) {
            //Only -1 here because new active kit isn't yet added to the list of used kits, therefor the kit to be unregistered is the last in the list.
            Kit lastKit = player.getUsedKits().get(player.getUsedKits().size() - 1);
            if (lastKit != null) unregisterAbilities(lastKit);
        }

        Kit currentKit = player.getActiveKit();
        if (currentKit == null) {
            player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 3, 2);
            player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 3, 4);
            player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 3, 1);
            return;
        }

        player.addUsedKit(currentKit);
        player.getPlayer().sendMessage(String.format("%s You have equipped %s%s!", GameCore.getKitPrefix(), player.getActiveKit().getName(), ChatColor.WHITE));
        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 3, 2);
        plugin.getServer().getLogger().info(String.format("[KIT] %s has been assigned %s", player.getPlayer().getName(), player.getActiveKit().getName()));
    }

    public static void addKitPlayer(KitPlayer player) {
        if (getKitPlayers().contains(player)) return;
        kitPlayers.add(player);
    }

    public static void removeKitPlayer(KitPlayer kitPlayer) {
        if (kitPlayer == null) return;
        if (getKitPlayers().contains(kitPlayer)) kitPlayers.remove(kitPlayer);
        if (kitPlayer.getUsedKits().isEmpty()) return;
        if (kitPlayer.getActiveKit() == null) {
            //-2 because -1 would just take it back to the last item in the list?
            Kit lastKit = kitPlayer.getUsedKits().get(kitPlayer.getUsedKits().size() - 2);
            if (lastKit != null) unregisterAbilities(lastKit);
            return;
        }
        unregisterAbilities(kitPlayer.getActiveKit());
    }

    public static void gameStarts() {
        for (KitPlayer kitPlayer : getKitPlayers()) {
            if (kitPlayer == null || kitPlayer.getActiveKit() == null) return;
            kitPlayer.equip();
            registerAbilities(kitPlayer, kitPlayer.getActiveKit());
        }
    }

    public static void gameEnds() {
        for (KitPlayer kitPlayer : getKitPlayers()) {
            removeKitPlayer(kitPlayer);
        }
    }

    public static List<KitPlayer> getKitPlayers() {
        return kitPlayers;
    }

    public static List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        for (KitPlayer kitPlayer : getKitPlayers()) {
            players.add(kitPlayer.getPlayer());
        }

        return players;
    }

    public static KitPlayer getKitPlayerFromPlayer(Player player) {
        for (KitPlayer kitPlayer : getKitPlayers()) {
            if (kitPlayer.getPlayer().getUniqueId() == player.getUniqueId()) return kitPlayer;
        }

        return null;
    }

    public static void unregisterAbilities(Kit lastKit) {
        for (Ability ability : lastKit.getAbilities()) {

            if (ability instanceof ChargedAbility) {
                ChargedAbility chargedAbility = (ChargedAbility) ability;
                Bukkit.getScheduler().cancelTask(chargedAbility.getTaskId());
            }

            HandlerList.unregisterAll(ability);
            plugin.getLogger().info(String.format("[LISTENER] Unregistered listener: %s", ability.getClass().getSimpleName()));
        }
    }

    public static void registerAbilities(KitPlayer kitPlayer, Kit currentKit) {
        for (Ability ability : currentKit.getAbilities()) {

            if (ability instanceof ChargedAbility) {
                ChargedAbility chargedAbility = (ChargedAbility) ability;
                if (chargedAbility.startsWithMaxCharges()) chargedAbility.setCharges(chargedAbility.getMaxCharges());
                if (!chargedAbility.passivelyGainCharges()) return;
                int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(GameCore.getInstance(), () -> {

                    if (chargedAbility.getCurrentCharges() >= chargedAbility.getMaxCharges()) return;
                    chargedAbility.addCharge();

                }, 20, chargedAbility.getSecondsBetweenCharge() * 20);

                chargedAbility.setTaskId(taskid);

            }

            if (ability instanceof AfterConstruct) ((AfterConstruct) ability).onConstruct();
            plugin.getLogger().info(String.format("[LISTENER] Registered listener: %s", ability.getClass().getSimpleName()));
            plugin.getServer().getPluginManager().registerEvents(ability, plugin);
            ability.setKitPlayer(kitPlayer);
        }
    }

    public static boolean isOnSameTeam(Player player, Player compassHolder) {
        KitPlayer player1 = getKitPlayerFromPlayer(player);
        KitPlayer player2 = getKitPlayerFromPlayer(compassHolder);

        return player1.getTeam().getSide() == player2.getTeam().getSide();
    }
}
