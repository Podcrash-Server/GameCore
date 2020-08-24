package com.podcrash.gamecore.title;

import com.podcrash.gamecore.GameModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TitleManager extends GameModule {

    public TitleManager() {
        super("title");
    }

    public void spawnTitle(String text, Player player) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
        armorStand.setSmall(true);
        Slime slime = (Slime) player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
        slime.setSize(-2);
        slime.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
        slime.setPassenger(armorStand);
        player.setPassenger(slime);
    }
}
