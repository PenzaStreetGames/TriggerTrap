package com.sosungersteam.triggertrap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.persons.Somov;
import com.sosungersteam.triggertrap.tools.WorldContactListener;
import com.sosungersteam.triggertrap.tools.WorldCreator;

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
    private String mapName;
    public Player player;

    Sound sound;
   //TODO сделать переход из комнаты в комнату, вынести комнаты в список, получать комнату по номеру двери, инициализировать комнаты, при этом сохранять персонажа в том же мире(постараться)
    public PlayScreen(TriggerTrap game,String mapName){
        atlas = new TextureAtlas("sprites/texture_pack.pack");
        this.game=game;
        this.mapName = mapName;
        setMusic();
        loadMap(mapName);
    }
    @Override
    public void show() {

    }

    public TextureAtlas getAtlas() {
        return atlas;
    }
    public void loadMap(String mapName){
        maploader = new TmxMapLoader();
        map = maploader.load(mapName);
        renderer = new OrthogonalTiledMapRenderer(map,1/16f);
        camera = new OrthographicCamera(32,18);
        world = new World(new Vector2(0,0),true); // create World container and gravity
        b2dr=new Box2DDebugRenderer();
        world.setContactListener(new WorldContactListener()); //создание взаимодействия физических объектов мира
        somov = new Somov(world,this); //TODO:делать так чтобы не создавался новый сомов и игрок, а просто передавался в другой мир.
        player = new Player(somov);
    }
    public void update (float delta){
        player.handleInput(delta);
        world.step(1/60f,6,2); // change later
        somov.update(delta); // абстрагировать от сомова к персон
        camera.position.x = player.person.body.getPosition().x;
        camera.position.y = player.person.body.getPosition().y;
        camera.update();
        renderer.setView(camera);
        System.out.println(player.person.body.getPosition());
    }

    @Override
    public void render(float delta) {
        update(delta); // updates map
        camera.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(); // renders map
        drawLvl();
        b2dr.render(world,camera.combined);
    }
    private void drawLvl(){
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        somov.draw(game.batch);
        game.batch.end();

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
