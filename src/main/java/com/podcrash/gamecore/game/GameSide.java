package com.podcrash.gamecore.game;

import org.bukkit.ChatColor;

public interface GameSide {

    String getDisplayName();

    ChatColor getColor();

    int side(); //basically a replacement for an enum ordinal

}
