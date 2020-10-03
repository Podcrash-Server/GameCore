package com.podcrash.gamecore.kits.abilitytype;

import com.podcrash.gamecore.kits.KitPlayer;;
import com.podcrash.gamecore.kits.KitPlayerManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public interface Drop {

    @EventHandler
    default void drop(PlayerDropItemEvent e) {
        KitPlayer kitPlayer = KitPlayerManager.getKitPlayerFromPlayer(e.getPlayer());
        if (kitPlayer == null) return;
        if (kitPlayer != getKitPlayer()) return;
        if (kitPlayer.getPlayer().getItemInHand().getType() != getItemType()) return;
        e.setCancelled(true);
        if (this instanceof Cooldown) {
            if (!((Cooldown) this).hasCooldown()) return; //redundant? Assuming if this implements Cooldown it has a cooldown...
            if (((Cooldown) this).onCooldown()) {
                getKitPlayer().getPlayer().sendMessage(((Cooldown) this).getCooldownMessage());
                return;
            }
        }

        doAbility();

    }

    KitPlayer getKitPlayer();
    Material getItemType();
    void doAbility();
}
