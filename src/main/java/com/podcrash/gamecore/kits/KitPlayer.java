package com.podcrash.gamecore.kits;

import com.podcrash.gamecore.GameCore;
import com.podcrash.gamecore.kits.abilitytype.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class KitPlayer {

    private Player player;
    private List<Kit> usedKits;
    private Kit activeKit;
    private long empoweredAt, empoweredFor = 0;
    private long silencedAt, silencedFor = 0;
    private int kills;

    public KitPlayer(Player player) {
        this.player = player;
        this.usedKits = new ArrayList<>();
        this.kills = 0;

        selectKit(null);
        KitPlayerManager.addKitPlayer(this);
    }

    public int getKillCount() {
        return kills;
    }

    public void addKill() {
        this.kills++;
    }

    public void resetKills() {
        this.kills = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Kit> getUsedKits() {
        return usedKits;
    }

    public void addUsedKit(Kit kit) {
        getUsedKits().add(kit);
    }

    public Kit getActiveKit() {
        return activeKit;
    }

    public void selectKit(Kit kit) {
        List<Kit> kits = getUsedKits();

        if (kit != null) {
            for (Kit usedKit : kits) {

                if (usedKit.getClass() != kit.getClass()) continue;
                if (usedKit instanceof Cooldown) {
                    if (!((Cooldown) usedKit).onCooldown()) continue;
                    getPlayer().sendMessage(((Cooldown) usedKit).getCooldownMessage());
                    getPlayer().playSound(getPlayer().getLocation(), Sound.SILVERFISH_KILL, 3, 2);
                    return;
                }
            }
        }

        this.activeKit = kit;
        if (kit != null) this.activeKit.setLastUsed(System.currentTimeMillis());
        KitPlayerManager.register(this);

    }

    public void equip() {
        getPlayer().getInventory().clear();
        getPlayer().getEquipment().clear();
        if (getActiveKit() == null) {
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Selector");
            item.setItemMeta(meta);

            getPlayer().getInventory().setItem(4, item);
            return;
        }
        getPlayer().getEquipment().setArmorContents(getActiveKit().getArmor());
        getPlayer().getInventory().setItem(0, getActiveKit().getWeapon());

        if (getActiveKit().getItem().getType() != getActiveKit().getWeapon().getType() && !itemIsArmor(getActiveKit().getItem()))
            getPlayer().getInventory().setItem(1, new ItemStack(getActiveKit().getItem().getType(), 1));

        getPlayer().updateInventory();
    }

    private boolean itemIsArmor(ItemStack item) {

        //diamond
        if (item.getType() == Material.DIAMOND_HELMET || item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.DIAMOND_LEGGINGS || item.getType() == Material.DIAMOND_BOOTS)
            return true;

        //iron
        if (item.getType() == Material.IRON_HELMET || item.getType() == Material.IRON_CHESTPLATE || item.getType() == Material.IRON_LEGGINGS || item.getType() == Material.IRON_BOOTS)
            return true;

        //chain
        if (item.getType() == Material.CHAINMAIL_HELMET || item.getType() == Material.CHAINMAIL_CHESTPLATE || item.getType() == Material.CHAINMAIL_LEGGINGS || item.getType() == Material.CHAINMAIL_BOOTS)
            return true;

        //gold
        if (item.getType() == Material.GOLD_HELMET || item.getType() == Material.GOLD_CHESTPLATE || item.getType() == Material.GOLD_LEGGINGS || item.getType() == Material.GOLD_BOOTS)
            return true;

        //leather
        if (item.getType() == Material.LEATHER_HELMET || item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_LEGGINGS || item.getType() == Material.LEATHER_BOOTS)
            return true;

        return false;
    }

    public void empower(long time) {
        this.empoweredFor = time;
        this.empoweredAt = System.currentTimeMillis();
    }

    public boolean isEmpowered() {
        return getEmpoweredFor() * 1000L > System.currentTimeMillis() - getEmpoweredAt();
    }

    public long getEmpoweredFor() {
        return empoweredFor;
    }

    public long getEmpoweredAt() {
        return empoweredAt;
    }

    public void silence(long time) {
        this.silencedFor = time;
        this.silencedAt = System.currentTimeMillis();
    }

    public long getSilencedAt() {
        return silencedAt;
    }

    public long getSilencedFor() {
        return silencedFor;
    }

    public boolean isSilenced() {
        return (System.currentTimeMillis() - getSilencedAt()) < getSilencedFor() * 1000L;
    }

    public String getDebuffMessage(String debuff, int duration) {
        return String.format("%s You have been %s%s%s for %s%s%s seconds!", GameCore.getKitPrefix(), ChatColor.GREEN, debuff, ChatColor.GRAY, ChatColor.GREEN, duration, ChatColor.GRAY);
    }

    public String getCannotUseMessage(String reason) {
        return String.format("%s You cannot use this ability while %s%s%s!", GameCore.getKitPrefix(), ChatColor.GREEN, reason, ChatColor.GRAY);
    }

}
