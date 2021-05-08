package com.sosungersteam.triggertrap.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sosungersteam.triggertrap.view.screens.MenuScreen;
import com.sosungersteam.triggertrap.view.screens.PlayScreen;
import com.sosungersteam.triggertrap.model.physics.WorldContactListener;
import com.sosungersteam.triggertrap.model.physics.WorldCreator;
import com.sosungersteam.triggertrap.view.screens.UI;

public class Renderer {
    private static Renderer renderer = null;
    public PlayScreen playScreen;
    public MenuScreen menuScreen;
    public World world;
    public Box2DDebugRenderer box2DDebugRenderer;
    public OrthogonalTiledMapRenderer orthogonalRenderer;
    private Viewport gameport;
    private OrthographicCamera gamecam;
    public com.sosungersteam.triggertrap.view.screens.UI UI;

    public TextureAtlas atlas;

    private Renderer() {
        atlas = new TextureAtlas("sprites/texture_pack.pack");
    }

    public static Renderer get() {
        if (renderer == null)
            renderer = new Renderer();
        return renderer;
    }

    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public void setUI(UI UI) {
        this.UI = UI;
    }

    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void createNewWorld(TiledMap map) {
        if (orthogonalRenderer == null) {
            orthogonalRenderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
            gameport=new StretchViewport(32,18,gamecam); //TODO
        }
        orthogonalRenderer.setMap(map);
        box2DDebugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0,0),true); // create World container and gravity
        new WorldCreator(world, map);
        world.setContactListener(new WorldContactListener()); //создание взаимодействия физических объектов мира
    }
}
