package com.podcrash.gamecore.kits.abilitytype;

import com.podcrash.gamecore.kits.KitPlayer;;
import com.podcrash.gamecore.kits.KitPlayerManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public interface Drop extends IAbility {

    @EventHandler
    default void drop(PlayerDropItemEvent e) {
        KitPlayer kitPlayer = KitPlayerManager.getKitPlayerFromPlayer(e.getPlayer());
        if (kitPlayer == null) return;
        if (kitPlayer.getPlayer().getItemInHand().getType() != getItemType()) return;
        e.setCancelled(true);
        if (this instanceof Cooldown) {
            if (!((Cooldown) this).hasCooldown()) return; //redundant? Assuming if this implements Cooldown it has a cooldown...
            if (((Cooldown) this).onCooldown()) {
                kitPlayer.getPlayer().sendMessage(((Cooldown) this).getCooldownMessage());
                return;
            }
        }

        doAbility();
        kitPlayer.getPlayer().sendMessage(this.getUsedMessage());
        if (this instanceof Cooldown) ((Cooldown) this).setLastUsed(System.currentTimeMillis());

    }

    Material getItemType();
    void doAbility();
}
