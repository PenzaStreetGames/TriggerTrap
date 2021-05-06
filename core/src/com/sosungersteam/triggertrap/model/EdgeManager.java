package com.sosungersteam.triggertrap.model;

import com.sosungersteam.triggertrap.model.map.Edge;

public class EdgeManager implements MapObjectManager<Edge> {
    private static EdgeManager edgeManager = null;

    private EdgeManager() {

    }

    public static EdgeManager get() {
        if (edgeManager == null)
            edgeManager = new EdgeManager();
        return edgeManager;
    }

    @Override
    public void load() {

    }

    @Override
    public Edge getById(int id) {
        return null;
    }
}
