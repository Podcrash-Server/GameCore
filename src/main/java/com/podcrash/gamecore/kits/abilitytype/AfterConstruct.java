package com.podcrash.gamecore.kits.abilitytype;

import org.bukkit.entity.Player;

public interface AfterConstruct extends IAbility {

    Player getPlayer();

    default void onConstruct() {
        if (getPlayer() != null) afterConstruct();
    }

    void afterConstruct();

}
