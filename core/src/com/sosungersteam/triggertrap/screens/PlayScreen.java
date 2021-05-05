package com.sosungersteam.triggertrap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.sprites.Somov;
import com.sosungersteam.triggertrap.tools.WorldCreator;


import java.util.Iterator;

public class PlayScreen implements Screen {
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private TriggerTrap game;
    private Music mainmenu;
    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera camera;
    private TextureAtlas atlas;

    private Somov somov;
    private FitViewport gamePort;
    Texture studentImage;
    Rectangle somovRect;
    Sound sound;
    private Array<Rectangle> students;
    private long lastDropTime;

    public PlayScreen(TriggerTrap game){
        atlas = new TextureAtlas("somov_pack.pack");
        this.game=game;
        camera = new OrthographicCamera();
        //sound = Gdx.audio.newSound(Gdx.files.internal("wilhelm_scream.mp3"));
        setMusic();
        maploader = new TmxMapLoader();
        map = maploader.load("maps/memrea_hall.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/16f);
        camera.setToOrtho(false,16,9);


        world = new World(new Vector2(0,0),true); // create World container and gravity
        b2dr=new Box2DDebugRenderer();

        new WorldCreator(world,map);
        somov = new Somov(world,this);



    }
    @Override
    public void show() {

    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void handleInput(float delta){ // testing camera moves
        float vx = 0, vy = 0;
        float velocity_scale = 8*0.5f;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            vx = velocity_scale;
            //TODO: add camera moves
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            vy = velocity_scale;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            vy = -velocity_scale;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            vx = -velocity_scale;
        }
        somov.b2body.setLinearVelocity(vx, vy);
    }
    public void update (float delta){
        handleInput(delta);
        world.step(1/60f,6,2); // change later
        somov.update(delta);
        camera.position.x=somov.b2body.getPosition().x;
        camera.position.y=somov.b2body.getPosition().y;
        camera.update();
        renderer.setView(camera);
        //System.out.println(somov.b2body.getPosition());
    }
    @Override
    public void render(float delta) {
        update(delta); // updates map
        camera.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(); // renders map
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        somov.draw(game.batch);
        game.batch.end();
        b2dr.render(world,camera.combined);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
    }

    private void setMusic(){
        mainmenu = Gdx.audio.newMusic(Gdx.files.internal("music/052. Uwa!! So HEATS!!.mp3"));
        mainmenu.play();
        mainmenu.setLooping(true);
        mainmenu.setVolume(0.1f);
    }

}
