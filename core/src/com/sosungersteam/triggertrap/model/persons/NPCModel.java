package com.sosungersteam.triggertrap.model.persons;

import com.badlogic.gdx.math.Vector2;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.managers.RoomManager;
import com.sosungersteam.triggertrap.model.map.Room;

public class NPCModel {
    public int id;
    public String textureName;
    public Room room;
    public Vector2 spawnPosition;
    public Person.Direction direction;

    public NPCModel(int id, String textureName, int roomId, float spawnX, float spawnY,
                    Person.Direction direction) {
        this.id = id;
        this.textureName = textureName;
        this.room = RoomManager.get().getById(roomId);
        this.spawnPosition = new Vector2(spawnX, spawnY);
        this.direction = direction;
    }
}
