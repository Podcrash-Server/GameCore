package com.podcrash.gamecore.kits.abilitytype;

import com.podcrash.gamecore.kits.KitPlayer;
import com.podcrash.gamecore.kits.KitPlayerManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Interact extends IAbility {

    @EventHandler
    default void interact(PlayerInteractEvent e) {
        //should never happen but just in case
        if (e.getPlayer() == null) return;

        KitPlayer kitPlayer = KitPlayerManager.getKitPlayerFromPlayer(e.getPlayer());
        if (kitPlayer == null) return;
        if (e.getItem() == null) return;
        if (e.getItem().getType() != getItemType()) return;

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
