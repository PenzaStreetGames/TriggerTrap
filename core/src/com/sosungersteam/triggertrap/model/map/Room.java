package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Room {
    public int id;
    public Array<Door> doors;
    public TiledMap tiledMap;
    public World world;
    public int height;
    public int width;
}
