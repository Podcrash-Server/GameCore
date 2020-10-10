package com.podcrash.game.kit.abilitytype;

import org.bukkit.entity.Player;

public interface AfterConstruct extends IAbility {

    Player getPlayer();

    default void onConstruct() {
        if (getPlayer() != null) afterConstruct();
    }

    void afterConstruct();

}
