package com.podcrash.game.oldkit;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class Ability implements Listener {

    private long lastUsed;
    private KitPlayer kitPlayer;

    public abstract String getName();
    public abstract ItemStack getItem();

    public KitPlayer getKitPlayer() {
        return kitPlayer;
    }

    public void setKitPlayer(KitPlayer kitPlayer) {
        this.kitPlayer = kitPlayer;
    }

    public long getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(long time) {
        this.lastUsed = time;
    }

}
