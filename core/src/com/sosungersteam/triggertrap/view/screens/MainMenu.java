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
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.view.Renderer;


public class MainMenu implements Screen {
    protected Stage stage;
    private Viewport viewport;
    private ImageTextButton playButton;
    private ImageTextButton creditsButton;
    private ImageTextButton exitButton;
    private Label title;
    private Texture texture;
    private TextureRegion region;
    public static int buttonWidth = 64;
    public static int buttonHeight = 16;

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
        mainTable.top();
        mainTable.setFillParent(true);
        BitmapFont font = createFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font=font;

        region = Renderer.get().atlas.findRegion("interface");
        this.texture = region.getTexture();

        TextureRegion texture = new TextureRegion(this.texture,  region.getRegionX(), region.getRegionY() + 16, buttonWidth, buttonHeight);
        title = new Label("Trigger Trap", labelStyle);
        mainTable.add(title);
        mainTable.row();
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
        stage.addActor(mainTable);
    }

    public void addButtons(Table table,ImageTextButton button){
        table.add(button);
        table.row();

    }
    public BitmapFont createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/psg-font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=30;
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
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        TriggerTrap.triggerTrap.batch.setProjectionMatrix(stage.getCamera().combined);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
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
