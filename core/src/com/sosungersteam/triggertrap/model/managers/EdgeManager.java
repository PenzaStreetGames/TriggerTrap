package com.sosungersteam.triggertrap.model.managers;

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
        edges.add(new Edge(3, 1, 2));
        edges.add(new Edge(4, 4, 5));
        edges.add(new Edge(5, 5, 4));
        edges.add(new Edge(6, 6, 1));
        edges.add(new Edge(7, 7, 1));
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
