package com.sosungersteam.triggertrap.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.model.managers.NPCManager;
import com.sosungersteam.triggertrap.model.persons.Person;
import com.sosungersteam.triggertrap.view.Renderer;

public class PlayScreen implements Screen {

    private TriggerTrap game;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    public Stage stage;

    public PlayScreen(TriggerTrap game){
        atlas = Renderer.get().atlas;
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
        Person person = GameController.get().player.person;
        person.update(delta);
        NPCManager.get().updatePeopleView(GameController.get().getTargetRoom(), delta);
        //if (GameController.get().personage != null)
        //    GameController.get().personage.update(delta);
        correctView(person);
        Renderer.get().orthogonalRenderer.setView(camera);
    }
    public void entryView(Person person){
        MapProperties prop = GameController.get().getTargetRoom().tiledMap.getProperties();
        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        int mapPixelWidth = mapWidth * tilePixelWidth;
        int mapPixelHeight = mapHeight * tilePixelHeight;
        boolean negX = (person.body.getPosition().x-camera.viewportWidth/2)<0;
        boolean posX = (person.body.getPosition().x+camera.viewportWidth/2)>mapPixelWidth/1/16f;
        boolean negY=(person.body.getPosition().y-camera.viewportHeight/2)<0;
        boolean posY = (person.body.getPosition().y+camera.viewportHeight/2)>mapPixelHeight/1/16f;
        if (posX){
            camera.position.x-=(camera.position.x+camera.viewportWidth/2)-mapPixelWidth/1/16f; // work
        }
        if (negX){

            camera.position.x=Math.abs(camera.viewportWidth/2);
        }
        if (posY){
            camera.position.y-=(camera.position.y+camera.viewportHeight/2)-mapPixelHeight/1/16f;//work
        }
        if (negY){

            camera.position.y=Math.abs(camera.viewportHeight/2);;
        }
    }
    public void correctView(Person person){
        MapProperties prop = GameController.get().getTargetRoom().tiledMap.getProperties();
        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        int mapPixelWidth = mapWidth * tilePixelWidth;
        int mapPixelHeight = mapHeight * tilePixelHeight;
        boolean negX = (person.body.getPosition().x-camera.viewportWidth/2)<=0;
        boolean posX = (person.body.getPosition().x+camera.viewportWidth/2)>=mapPixelWidth/1/16f;
        boolean negY=(person.body.getPosition().y-camera.viewportHeight/2)<=0;
        boolean posY = (person.body.getPosition().y+camera.viewportHeight/2)>=mapPixelHeight/1/16f;
        if (posX){
            if (camera.position.x>=person.body.getPosition().x)
                camera.position.x=person.body.getPosition().x;
        }
        else if (negX){
            if (camera.position.x<=person.body.getPosition().x)
                camera.position.x=person.body.getPosition().x;
        }
        else {
            camera.position.x=person.body.getPosition().x;
        }
        if (posY){
            if (camera.position.y>=person.body.getPosition().y)
                camera.position.y=person.body.getPosition().y;
        }
        else if (negY){
            if (camera.position.y<=person.body.getPosition().y)
                camera.position.y=person.body.getPosition().y;
        }
        else{
            camera.position.y=person.body.getPosition().y;
        }
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
        if (GameController.get().gameMode== GameController.GameMode.DIALOG){
            Renderer.get().UI.dialogue.render();
        }

    }
    private void drawLvl(){
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        Person person = GameController.get().player.person;
        person.draw(game.batch);
        NPCManager.get().drawPeople(GameController.get().getTargetRoom());
        //if (GameController.get().personage != null)
        //    GameController.get().personage.draw(game.batch);
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
        //if (GameController.get().gameMode== GameController.GameMode.PLAYING && Renderer.get().UI!=null && Renderer.get().UI.dialogue!=null){
           // Renderer.get().UI.dialogue.dispose();
        //}
    }

}
