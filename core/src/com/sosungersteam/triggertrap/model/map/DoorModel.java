package com.sosungersteam.triggertrap.model.map;

public class DoorModel {
    public int id;
    public Room room;
    public Edge edge;

    public DoorModel(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}
