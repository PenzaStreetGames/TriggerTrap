package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.math.Vector2;
import com.sosungersteam.triggertrap.model.RoomManager;

public class SpawnPoint {
    public Vector2 point;
    public int roomId;
    public Room room;
    public int id;

    public SpawnPoint(int id, int roomId) {
        this.id = id;
        this.roomId = roomId;
        room = RoomManager.get().getById(roomId);
    }

    public void setPoint(Vector2 point) {
        this.point = point;
    }
}
