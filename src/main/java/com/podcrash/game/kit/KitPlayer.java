package com.podcrash.game.kit;

import com.podcrash.game.kit.abilitytype.Cooldown;
import com.podcrash.gamecore.GameCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KitPlayer {

    private Player player;
    private List<Kit> usedKits;
    private Kit activeKit;
    private long empoweredAt, empoweredFor = 0;
    private long silencedAt, silencedFor = 0;
    private int kills;
    private List<Material> armorMaterials = Arrays.asList(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
            Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
            Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
            Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS,
            Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS);

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
        if (getActiveKit() == null) return;
        getPlayer().getEquipment().setArmorContents(getActiveKit().getArmor());
        getPlayer().getInventory().setItem(0, getActiveKit().getWeapon());

        if (getActiveKit().getItem().getType() != getActiveKit().getWeapon().getType() && !itemIsArmor(getActiveKit().getItem()))
            getPlayer().getInventory().setItem(1, new ItemStack(getActiveKit().getItem().getType(), 1));

        getPlayer().updateInventory();
    }

    private boolean itemIsArmor(ItemStack item) {
        if (armorMaterials.contains(item.getType())) return true;
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
