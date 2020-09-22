package com.podcrash.gamecore.kits.abilitytype;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Interact {

    @EventHandler
    void interact(PlayerInteractEvent e);

    void doAbility();
}
