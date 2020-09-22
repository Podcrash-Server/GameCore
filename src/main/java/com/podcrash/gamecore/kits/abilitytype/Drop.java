package com.podcrash.gamecore.kits.abilitytype;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public interface Drop {

    @EventHandler
    void drop(PlayerDropItemEvent e);

    void doAbility();
}
