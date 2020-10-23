package com.podcrash.game.map;

import java.util.ArrayList;
import java.util.List;

public class MapManager {

    private final List<GameMap> loadedMaps;

    public MapManager() {
        this.loadedMaps = new ArrayList<>();
    }

    public void loadMap(GameMap gameMap) {
        loadedMaps.add(gameMap);
    }
}
