package com.sosungersteam.triggertrap.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.model.persons.Somov;
import com.sosungersteam.triggertrap.view.Renderer;

public class PlayScreen implements Screen {

    private TriggerTrap game;
    private OrthographicCamera camera;
    private TextureAtlas atlas;

    public PlayScreen(TriggerTrap game){
        atlas = new TextureAtlas("sprites/texture_pack.pack");
        this.game=game;
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
        game.batch.setProjectionMatrix(game.renderer.UI.stage.getCamera().combined);
        Renderer.get().orthogonalRenderer.render(); // renders map
        drawLvl();
        game.renderer.UI.stage.draw();
        Renderer.get().box2DDebugRenderer.render(Renderer.get().world, camera.combined);
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
        game.renderer.UI.viewport.setScreenSize(width, height);
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
