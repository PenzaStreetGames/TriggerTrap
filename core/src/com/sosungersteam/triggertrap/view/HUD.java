package com.sosungersteam.triggertrap.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;

import java.util.HashMap;

public class HUD {
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

    public HUD(SpriteBatch sb){
        viewport = new FitViewport(32,18, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        region = Renderer.get().atlas.findRegion("interface");
        texture = region.getTexture();

        Color buttonColor = new Color(1,1,1,0.45f);
        createButton(0, 5, 5, 2, 2, Player.Buttons.UP);
        createButton(1, 2.8f,2.75f, 2,2, Player.Buttons.LEFT);
        createButton(2, 7.2f,2.75f, 2,2, Player.Buttons.RIGHT);
        createButton(3, 5,0.5f, 2,2, Player.Buttons.DOWN);
        createButton(4, 25, 2.75f, 2, 2, Player.Buttons.ACT);
    }

    public void createButton(int number, float x, float y, float width, float height, final Player.Buttons signal) {
        Color buttonColor = new Color(1,1,1,0.45f);
        TextureRegion texture = new TextureRegion(this.texture,  region.getRegionX() + buttonWidth * number, region.getRegionY(), buttonWidth, buttonHeight);
        ImageButton button = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        button.setPosition(x, y);
        button.setSize(width, height);
        button.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                GameController.get().player.handleButtons(signal, false);
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
    }
}
