package com.podcrash.game.kit.abilitytype;

import org.bukkit.ChatColor;

public interface IAbility {

    String getName();

    default String getUsedMessage() {
        return String.format("%s You have used %s%s", "GameCore.getKitPrefix()", ChatColor.GREEN, getName());
    }

}
