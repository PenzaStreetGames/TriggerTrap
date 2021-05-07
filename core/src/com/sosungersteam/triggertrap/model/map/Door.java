package com.sosungersteam.triggertrap.model.map;

public class Door {
    public int id;
    public int roomId;
    public int edgeId;
    public Room room;
    public Edge edge;
    private String name;
    public DoorObject doorObject;
    public SpawnPoint spawnPoint;

    public Door(int id, int roomId) {
        this.id = id;

    }

    public void attachDoorObject(DoorObject object) {
        doorObject = object;
        this.name = name;
        doorObject.door = this;
    }

    public void setEdgeId(int edgeId) {
        this.edgeId = edgeId;
    }

    public void setSpawnPoint(SpawnPoint spawnPoint) {
        this.spawnPoint = spawnPoint;
    }
}
