package com.podcrash.gamecore.kits;

import com.podcrash.gamecore.GameCore;
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
        player.equip();
        player.getPlayer().resetMaxHealth();
        if (!player.getUsedKits().isEmpty()) {
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
        registerAbilities(player, currentKit);

        player.addUsedKit(currentKit);
        player.getPlayer().sendMessage(String.format("%s You have equipped %s%s!", GameCore.getKitPrefix(), player.getActiveKit().getName(), ChatColor.WHITE));
        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 3, 2);
        plugin.getServer().getLogger().info(String.format("[KIT] %s has been assigned %s", player.getPlayer().getName(), player.getActiveKit().getName()));
    }

    public static void addKitPlayer(KitPlayer player) {
        if (getKitPlayers().contains(player)) return;
        kitPlayers.add(player);
    }

    public static void removeKitPlayer(KitPlayer player) {
        if (!(getKitPlayers().contains(player))) return;
        kitPlayers.remove(player);

        kitPlayerDies(player);
    }

    public static void kitPlayerLeaves(KitPlayer kitPlayer) {
        if (kitPlayer.getActiveKit() == null) return;
        unregisterAbilities(kitPlayer.getActiveKit());
        for (Kit kit : kitPlayer.getUsedKits()) {
            System.out.println(kit.getName());
        }
    }

    public static void kitPlayerDies(KitPlayer player) {
        if (player.getActiveKit() == null) {
            player.equip();
            return;
        }
        unregisterAbilities(player.getActiveKit());
        player.selectKit(null);
    }

    public static List<KitPlayer> getKitPlayers() {
        return kitPlayers;
    }

    public static KitPlayer getKitPlayerFromPlayer(Player player) {
        for (KitPlayer kitPlayer : getKitPlayers()) {
            if (kitPlayer.getPlayer().getUniqueId() == player.getUniqueId()) return kitPlayer;
        }

        return null;
    }

    private static void unregisterAbilities(Kit lastKit) {
        for (Ability ability : lastKit.getAbilities()) {
            HandlerList.unregisterAll(ability);
            plugin.getLogger().info(String.format("[LISTENER] Unregistered listener: %s", ability.getClass().getSimpleName()));
        }
    }

    private static void registerAbilities(KitPlayer kitPlayer, Kit currentKit) {
        for (Ability ability : currentKit.getAbilities()) {
            plugin.getLogger().info(String.format("[LISTENER] Registered listener: %s", ability.getClass().getSimpleName()));
            plugin.getServer().getPluginManager().registerEvents(ability, plugin);
            ability.setKitPlayer(kitPlayer);
        }
    }

}
