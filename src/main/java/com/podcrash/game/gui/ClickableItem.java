package com.podcrash.game.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ClickableItem implements Listener {

    private final ItemStack itemStack;
    private final Consumer<InventoryClickEvent> inventoryClickEventConsumer;

    public ClickableItem(ItemStack itemStack, Consumer<InventoryClickEvent> inventoryClickEventConsumer) {
        this.itemStack = itemStack;
        this.inventoryClickEventConsumer = inventoryClickEventConsumer;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEventConsumer.accept(inventoryClickEvent);
    }
}
