package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Door extends InteractiveObjects {
    //public int id;
   // public Room room;
   // public Edge edge;
    private String name;

    public Door(World world, TiledMap map, Rectangle bounds,String name) {
        super(world, map, bounds);
        this.name = name;
        fixture.setUserData(this);
    }

    @Override
    public void onUse() {
        System.out.println(this.name);
    }
}
