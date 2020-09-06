package com.podcrash.gamecore.stats;

import com.podcrash.gamecore.game.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Stats<T extends Game> {

    private int wins;
    private int losses;
    private int kills;
    private int deaths;
}
