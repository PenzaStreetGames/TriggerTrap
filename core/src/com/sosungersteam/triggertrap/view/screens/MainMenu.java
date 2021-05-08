package com.sosungersteam.triggertrap.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.view.Renderer;

public class MainMenu implements Screen {
    protected Stage stage;
    private Viewport viewport;
    private ImageButton playButton;
    private ImageButton creditsButton;
    private ImageButton exitButton;
    public MainMenu(SpriteBatch sb){
        viewport = new FitViewport(1000,1000,new OrthographicCamera());
        viewport.apply();//???
        //camera pos??
        stage = new Stage(viewport,sb);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        Texture texture = new Texture("sprites/play.png");
        playButton = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Renderer.get().setPlayScreen(new PlayScreen(TriggerTrap.triggerTrap));// test
                TriggerTrap.triggerTrap.gameBegin();
            }
        });
        exitButton = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        creditsButton = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        creditsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("credits");
            }
        });
        addButtons(mainTable,playButton);
        addButtons(mainTable,creditsButton);
        addButtons(mainTable,exitButton);
        stage.addActor(mainTable);
    }

    public void addButtons(Table table,ImageButton button){
        table.add(button);
        table.row();

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        TriggerTrap.triggerTrap.batch.setProjectionMatrix(stage.getCamera().combined);
        stage.act();
        stage.draw();
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

    }
}
