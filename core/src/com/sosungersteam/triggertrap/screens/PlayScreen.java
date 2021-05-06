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
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.model.RoomManager;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.persons.Person;
import com.sosungersteam.triggertrap.persons.Somov;
import com.sosungersteam.triggertrap.tools.WorldContactListener;
import com.sosungersteam.triggertrap.tools.WorldCreator;
import com.sosungersteam.triggertrap.view.Renderer;

public class PlayScreen implements Screen {


    private TriggerTrap game;
    private OrthographicCamera camera;
    private TextureAtlas atlas;

   //TODO сделать переход из комнаты в комнату, вынести комнаты в список, получать комнату по номеру двери, инициализировать комнаты, при этом сохранять персонажа в том же мире(постараться)
    public PlayScreen(TriggerTrap game){
        atlas = new TextureAtlas("sprites/texture_pack.pack");
        this.game=game;

        Room targetRoom = GameController.get().getTargetRoom();
        camera = new OrthographicCamera(32,18);

    }
    @Override
    public void show() {

    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void update (float delta){
        GameController.get().player.handleInput(delta);
        Renderer.get().world.step(1/60f,6,2); // change later
        Somov somov = (Somov)GameController.get().player.person;
        somov.update(delta);
        camera.position.x = somov.body.getPosition().x;
        camera.position.y = somov.body.getPosition().y;
        camera.update();
        Renderer.get().orthogonalRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta); // updates map
        camera.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(game.hud.stage.getCamera().combined);
        Renderer.get().orthogonalRenderer.render(); // renders map
        drawLvl();
        game.hud.stage.draw();
        //Renderer.get().box2DDebugRenderer.render(Renderer.get().world, camera.combined);
    }
    private void drawLvl(){
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        Somov somov = (Somov)GameController.get().player.person;
        somov.draw(game.batch);
        game.batch.end();

    }
    @Override
    public void resize(int width, int height) {
        game.hud.viewport.setScreenSize(width, height);
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
    }

}
