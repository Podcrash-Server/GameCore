package com.podcrash.game.kit.abilitytype;

import com.podcrash.gamecore.GameCore;
import org.bukkit.ChatColor;
import java.text.DecimalFormat;

public interface Cooldown extends IAbility {

    float getCooldown();

    default boolean hasCooldown() {
        return getCooldown() != -1;
    }
    default boolean onCooldown() {
        return (System.currentTimeMillis() - getLastUsed()) < getCooldown() * 1000L;
    }
    default double cooldown() {
        return (getCooldown() - ((System.currentTimeMillis() - getLastUsed())) / 1000D);
    }

    long getLastUsed();
    void setLastUsed(long time);

    default String getCooldownMessage() {
        DecimalFormat decimalFormat = new DecimalFormat("##.#");
        return String.format(
                "%s %s%s%s cannot be used for %s%s%s minutes!",
                GameCore.getKitPrefix(),
                ChatColor.GREEN,
                getName(),
                ChatColor.GRAY,
                ChatColor.GREEN,
                decimalFormat.format(cooldown()/60),
                ChatColor.GRAY);
    }
}
