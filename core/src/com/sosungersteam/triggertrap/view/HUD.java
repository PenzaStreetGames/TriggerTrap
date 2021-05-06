package com.sosungersteam.triggertrap.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
    public Stage stage;
    private Viewport viewport;
    private Skin mySkin;
    private Texture texture;
    private ImageButton buttonUp;
    private ImageButton buttonDown;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    public HUD(SpriteBatch sb){
        viewport = new FitViewport(32,18,new OrthographicCamera());
        stage = new Stage(viewport,sb);
        Color buttonColor = new Color(1,1,1,0.45f);
        texture = new Texture("HUD/Arrow_Down.png");
        ImageButton buttonDown = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        buttonDown.setPosition(5,0.5f);
        buttonDown.setSize(2,2);
        buttonDown.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                    // смещается вниз
            }
        });
        buttonDown.setColor(buttonColor);


        texture=new Texture("HUD/Arrow_Up.png");
        ImageButton buttonUp = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        buttonUp.setPosition(5,5);
        buttonUp.setSize(2,2);
        buttonUp.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                // смещается вверх
            }
        });
        buttonUp.setColor(buttonColor);
        texture=new Texture("HUD/Arrow_Left.png");
        ImageButton buttonLeft = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        buttonLeft.setPosition(2.8f,2.75f);
        buttonLeft.setSize(2,2);
        buttonLeft.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                // смещается влево
            }
        });
        buttonLeft.setColor(buttonColor);
        texture=new Texture("HUD/Arrow_Right.png");
        ImageButton buttonRight = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        buttonRight.setPosition(7.2f,2.75f);
        buttonRight.setSize(2,2);
        buttonRight.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                // смещается влево
            }
        });
        buttonRight.setColor(buttonColor);
        stage.addActor(buttonUp);
        stage.addActor(buttonDown);
        stage.addActor(buttonLeft);
        stage.addActor(buttonRight);
    }
}
