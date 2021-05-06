package com.sosungersteam.triggertrap.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.screens.PlayScreen;
import com.sosungersteam.triggertrap.tools.WorldContactListener;
import com.sosungersteam.triggertrap.tools.WorldCreator;

public class Renderer {
    private static Renderer renderer = null;
    public PlayScreen playScreen;
    public World world;
    public Box2DDebugRenderer box2DDebugRenderer;

    private Renderer() {

    }

    public static Renderer get() {
        if (renderer == null)
            renderer = new Renderer();
        return renderer;
    }

    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void createNewWorld(TiledMap map) {
        box2DDebugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0,0),true); // create World container and gravity
        new WorldCreator(world, map);
        world.setContactListener(new WorldContactListener()); //создание взаимодействия физических объектов мира
    }
}
