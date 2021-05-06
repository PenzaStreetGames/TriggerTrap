package com.sosungersteam.triggertrap.model;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.map.Edge;

public class EdgeManager implements MapObjectManager<Edge> {
    private static EdgeManager edgeManager = null;
    public Array<Edge> edges = new Array<>();

    private EdgeManager() {

    }

    public static EdgeManager get() {
        if (edgeManager == null)
            edgeManager = new EdgeManager();
        return edgeManager;
    }

    @Override
    public void load() {
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(2, 3, 2));
    }

    @Override
    public Edge getById(int id) {
        for (Edge edge : edges) {
            if (edge.id == id)
                return edge;
        }
        return null;
    }
}
