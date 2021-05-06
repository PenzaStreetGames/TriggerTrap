package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.model.DoorManager;

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
