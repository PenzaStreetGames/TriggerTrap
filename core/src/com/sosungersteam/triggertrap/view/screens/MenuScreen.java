package com.sosungersteam.triggertrap.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.view.Renderer;

import java.util.HashMap;


public class MenuScreen implements Screen {

    public static enum Buttons {START, CREDITS, EXIT};
    protected Stage stage;
    private Viewport viewport;
    private ImageTextButton playButton;
    private ImageTextButton creditsButton;
    private ImageTextButton exitButton;
    private Label title;
    private Label.LabelStyle labelStyle;
    private Texture texture;
    private TextureRegion region;
    public BitmapFont font;
    public HashMap<Buttons, ImageTextButton> buttonMap = new HashMap<>();
    public static int buttonWidth = 64;
    public static int buttonHeight = 16;
    public static int screenWidth = 400;
    public static int screenHeight = 300;
    public static int screenButtonWidth = 160;
    public static int screenButtonHeight = 40;

    public MenuScreen(SpriteBatch sb){
        viewport = new FitViewport(screenWidth, screenHeight, new OrthographicCamera());
        viewport.apply();//???
        //camera pos??
        stage = new Stage(viewport,sb);
    }
    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        Table mainTable = new Table();
        mainTable.top();
        mainTable.setFillParent(true);
        font = createFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font=font;

        region = Renderer.get().atlas.findRegion("interface");
        this.texture = region.getTexture();

        TextureRegion texture = new TextureRegion(this.texture,  region.getRegionX(), region.getRegionY() + 16, buttonWidth, buttonHeight);
        title = new Label("Trigger Trap", labelStyle);

        int buttonsX = screenWidth / 2 - screenButtonWidth / 2;
        createButton(buttonsX, 200, screenButtonWidth, screenButtonHeight, Buttons.START, "play", texture);
        createButton(buttonsX, 150, screenButtonWidth, screenButtonHeight, Buttons.CREDITS, "credits", texture);
        createButton(buttonsX, 100, screenButtonWidth, screenButtonHeight, Buttons.EXIT, "exit", texture);

        mainTable.add(title);
        mainTable.row();
        /*
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(new TextureRegionDrawable(texture),
                new TextureRegionDrawable(texture),new TextureRegionDrawable(texture),font);

        playButton = new ImageTextButton("play",style);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Renderer.get().setPlayScreen(new PlayScreen(TriggerTrap.triggerTrap));// test
                TriggerTrap.triggerTrap.gameBegin();
            }
        });
        exitButton = new ImageTextButton("exit",style);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        creditsButton = new ImageTextButton("credits",style);
        creditsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("credits");
            }
        });
        addButtons(mainTable,playButton);
        addButtons(mainTable,creditsButton);
        addButtons(mainTable,exitButton);
        */
        stage.addActor(mainTable);
    }

    public void createButton(float x, float y, float width, float height, final Buttons signal, String label, TextureRegion buttonRegion) {

        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(new TextureRegionDrawable(buttonRegion),
                new TextureRegionDrawable(buttonRegion),new TextureRegionDrawable(buttonRegion), font);
        ImageTextButton button = new ImageTextButton(label, style);
        button.setPosition(x, y);
        button.setSize(width, height);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Renderer.get().menuScreen.handleButton(signal);
            }
        });
        stage.addActor(button);
        buttonMap.put(signal, button);
    }

    public void handleButton(Buttons button) {
        if (button == Buttons.START) {
            Renderer.get().setPlayScreen(new PlayScreen(TriggerTrap.triggerTrap));// test
            TriggerTrap.triggerTrap.gameBegin();
        }
        else if (button == Buttons.CREDITS) {
            System.out.println("credits");
        }
        else if (button == Buttons.EXIT) {
            Gdx.app.exit();
        }
    }

    public void addButtons(Table table,ImageTextButton button){
        table.add(button);
        table.row();

    }
    public BitmapFont createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/psg-rounded.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=20;
        parameter.borderWidth=1;
        parameter.color=Color.WHITE;
        parameter.shadowOffsetX=3;
        parameter.shadowOffsetY=3;
        parameter.shadowColor = new Color(0,0.5f,0,0.75f);
        BitmapFont font24 = generator.generateFont(parameter);
        generator.dispose();
        return  font24;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.6f,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        TriggerTrap.triggerTrap.batch.setProjectionMatrix(stage.getCamera().combined);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
