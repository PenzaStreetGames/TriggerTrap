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

    private Somov som;
    //private FitViewport gamePort;

    Texture somov;
    Texture studentImage;
    Rectangle somovRect;
    Sound sound;
    private Array<Rectangle> students;
    private long lastDropTime;

    public PlayScreen(TriggerTrap game){
        //atlas = new TextureAtlas("aasd");
        this.game=game;
        //create camera
        camera = new OrthographicCamera();
        //create port
        //gamePort = new FitViewport(game.virtual_width/TriggerTrap.pixelsMultiplier,game.virtual_height/TriggerTrap.pixelsMultiplier,camera);

        sound = Gdx.audio.newSound(Gdx.files.internal("wilhelm_scream.mp3"));
        //createSomov();
        setMusic();
        //studentImage = new Texture("student.png");
        //students = new Array<Rectangle>();
        //spawnStudent();
         //Map loading
        maploader = new TmxMapLoader();
        map = maploader.load("memrea_hall.tmx");
        MapProperties prop = map.getProperties();
        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        int mapPixelWidth = mapWidth * tilePixelWidth;
        int mapPixelHeight = mapHeight * tilePixelHeight;
        renderer = new OrthogonalTiledMapRenderer(map,1/100f);
        camera.setToOrtho(false, 5,5);


        world = new World(new Vector2(0,0),true); // create World container and gravity
        b2dr=new Box2DDebugRenderer();

        new WorldCreator(world,map);
        som = new Somov(world,this);



    }
    @Override
    public void show() {

    }
    public TextureAtlas getAtlas(){
        return atlas;
    }
    public void handleInput(float delta){ // testing camera moves
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && som.b2body.getLinearVelocity().x<=2 ){
           som.b2body.setLinearVelocity(0.1f,0); //TODO: add camera moves
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)&& som.b2body.getLinearVelocity().y<=2){
            som.b2body.applyLinearImpulse(new Vector2(0,0.1f),som.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)&& som.b2body.getLinearVelocity().y>=-2){
            som.b2body.applyLinearImpulse(new Vector2(0,-0.1f),som.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&& som.b2body.getLinearVelocity().x>=-2 ){
            som.b2body.applyLinearImpulse(new Vector2(-0.1f,0),som.b2body.getWorldCenter(),true);
        }
    }
    public void update (float delta){
        handleInput(delta);
        world.step(1/60f,6,2); // change later
        camera.position.x=som.b2body.getPosition().x;
        camera.position.y=som.b2body.getPosition().y;
        camera.update();
        renderer.setView(camera);
    }
    @Override
    public void render(float delta) {
        update(delta); // updates map
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world,camera.combined); // render b2dr debug lines
        renderer.render(); // renders map
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
        mainmenu = Gdx.audio.newMusic(Gdx.files.internal("Nitro Fun - Cheat Codes.mp3"));
        mainmenu.play();
        mainmenu.setLooping(true);
        mainmenu.setVolume(0.1f);
    }

}
