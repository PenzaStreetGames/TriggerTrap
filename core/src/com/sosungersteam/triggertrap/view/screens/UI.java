package com.sosungersteam.triggertrap.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.view.Renderer;

import java.util.HashMap;

public class UI {
    public Stage stage;
    public Viewport viewport;
    private Skin mySkin;
    private Texture texture;
    private ImageButton buttonUp;
    private ImageButton buttonDown;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    public HashMap<Player.Buttons, Button> buttonMap = new HashMap<>();
    public static int buttonWidth = 16;
    public static int buttonHeight = 16;
    public TextureAtlas.AtlasRegion region;
    BitmapFont font;
    public static float scale = 16;

    public UI(SpriteBatch sb){
        viewport = new StretchViewport(500,288, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        region = Renderer.get().atlas.findRegion("interface");
        texture = region.getTexture();

        Color buttonColor = new Color(1,1,1,0.45f);
        createButton(0, 5, 5, 2, 2, Player.Buttons.UP);
        createButton(1, 2.8f,2.75f, 2,2, Player.Buttons.LEFT);
        createButton(2, 7.2f,2.75f, 2,2, Player.Buttons.RIGHT);
        createButton(3, 5,0.5f, 8,8, Player.Buttons.DOWN);
        createButton(4, 25, 2.75f, 2, 2, Player.Buttons.ACT);

        //createDialogWindow();
    }

    public void createButton(int number, float x, float y, float width, float height, final Player.Buttons signal) {
        // Todo: сделать нормальные кнопки
        Color buttonColor = new Color(1,1,1,0.45f);
        TextureRegion texture = new TextureRegion(this.texture,  region.getRegionX() + buttonWidth * number, region.getRegionY(), buttonWidth, buttonHeight);
        final ImageButton button = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        button.setPosition(x, y);
        button.setSize(width, height);
        button.setScaleX(2);
        button.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                GameController.get().player.handleButtons(signal, false);
                GameController.get().player.singleHandleButtons(signal);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GameController.get().player.handleButtons(signal, true);
                return true;
            }
        });
        button.setColor(buttonColor);
        stage.addActor(button);
        buttonMap.put(signal, button);
        stage.setDebugAll(true);
    }

    public void createDialogWindow() {
        font = createFont();
        Texture texture = this.texture;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(new TextureRegionDrawable(texture),new TextureRegionDrawable(texture),
                new TextureRegionDrawable(texture),font);
        //TextButton btnYes = new TextButton("Yes",textButtonStyle);
        //TextButton btnNo = new TextButton("No",textButtonStyle);
        Window.WindowStyle windowStyle = new Window.WindowStyle(font, new Color(1,1,1,1), new TextureRegionDrawable(texture));

        final Dialog dialog = new Dialog("First Dialog",windowStyle){
            @Override
            public float getPrefWidth(){
                return 10f;
            }
            @Override
            public float getPrefHeight(){
                return 4f;
            }
        };
        dialog.setResizable(false);
        dialog.setMovable(false);
        dialog.setModal(true);
        /*
        btnYes.addListener(new InputListener(){
           @Override
           public boolean touchDown(InputEvent event, float x, float y,
                                    int pointer, int button) {
               System.out.println("YES");
               dialog.hide();
               dialog.cancel();
               dialog.remove();
               return true;
           }
        });
        btnNo.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                System.out.println("NO");
                dialog.hide();
                dialog.cancel();
                dialog.remove();
                return true;
            }
        });
        */
        //Table t = new Table();

        dialog.show(stage).setPosition(
                (10f),
                (5f));
       //stage.addActor(dialog);


    }
    public BitmapFont createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/psg-rounded.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=1;
        parameter.borderWidth=1;
        parameter.color=Color.WHITE;
        parameter.shadowOffsetX=0;
        parameter.shadowOffsetY=0;
        parameter.shadowColor = new Color(1,1,1,1f);
        BitmapFont font24 = generator.generateFont(parameter);
        generator.dispose();
        return  font24;
    }
}
