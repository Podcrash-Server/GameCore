package com.podcrash.gamecore.kits;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class Ability implements Listener {

    private long lastUsed = 0L;
    private KitPlayer kitPlayer;

    public Ability() {
    }

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
