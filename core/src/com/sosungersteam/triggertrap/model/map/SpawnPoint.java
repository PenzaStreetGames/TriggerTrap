package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.math.Vector2;
import com.sosungersteam.triggertrap.model.managers.DoorManager;
import com.sosungersteam.triggertrap.model.managers.RoomManager;

public class SpawnPoint {
    public Vector2 point;
    public int roomId;
    public Room room;
    public int doorId;
    public Door door;
    public int id;

    public SpawnPoint(int id, int roomId, int doorId) {
        this.id = id;
        this.roomId = roomId;
        this.doorId = doorId;
        room = RoomManager.get().getById(roomId);
        if (doorId != 0) {
            door = DoorManager.get().getById(doorId);
            door.setSpawnPoint(this);
        }
    }

    public void setPoint(Vector2 point) {
        this.point = point;
    }
}
