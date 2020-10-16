package com.podcrash.game.kit;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class Ability implements Listener {

    private final int slot;
    private final ItemStack itemStack;
    private final boolean canDrop;

    private long lastUsed;

    protected Ability(int slot, ItemStack itemStack, boolean canDrop) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.canDrop = canDrop;
        this.lastUsed = 0;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public boolean canDrop() {
        return canDrop;
    }

    public long getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }
}
