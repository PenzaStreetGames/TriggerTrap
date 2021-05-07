package com.sosungersteam.triggertrap.model.map;

import com.sosungersteam.triggertrap.model.managers.DoorManager;

public class Edge {
    public int id;
    public Door doorInto;
    public Door doorFrom;

    public Edge(int id, int doorFromId, int doorIntoId) {
        this.id = id;
        doorFrom = DoorManager.get().getById(doorFromId);
        doorInto = DoorManager.get().getById(doorIntoId);
        doorFrom.edge = this;
    }
}
