package com.podcrash.game.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class GameGui {

    private final Inventory inventory;

    public GameGui(String name, int slots) {
        this.inventory = Bukkit.createInventory(null, slots, ChatColor.translateAlternateColorCodes('&', name));
    }

    public void setItem(int slot, ItemStack itemStack) {
        inventory.setItem(slot, itemStack);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
