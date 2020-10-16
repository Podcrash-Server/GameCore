package com.podcrash.game.kit;

import java.util.Arrays;
import java.util.List;

public abstract class Kit {

    private final String name;

    private final List<Ability> abilities;

    protected Kit(String name, Ability... abilities) {
        this.name = name;
        this.abilities = Arrays.asList(abilities);
    }

    public String getName() {
        return name;
    }
}
