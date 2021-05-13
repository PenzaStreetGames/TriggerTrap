package com.sosungersteam.triggertrap.view.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.model.dialogs.Dialog;
import com.sosungersteam.triggertrap.view.Renderer;

import java.io.IOException;
import java.util.HashMap;

public class UI {
    public Stage stage;
    public Viewport viewport;
    private Texture texture;
    public static enum Buttons{LEFT,RIGHT,NEXT};
    private ImageTextButton DialogBtn1;
    private ImageTextButton DialogBtn2;
    private  ImageTextButton DialogBtn3;
    private Image DialogWindow;
    private Label TextWindow;
    private Label Hint;
    public HashMap<Player.Buttons, Button> buttonMap = new HashMap<>();
    public HashMap<Buttons,Button> DialogButtonMap = new HashMap<>();
    public HashMap<Buttons,String> buttonText = new HashMap<>();
    public static int buttonWidth = 16;
    public static int buttonHeight = 16;
    public TextureAtlas.AtlasRegion region;
    BitmapFont font;
    BitmapFont hintfont;

    public UI(SpriteBatch sb){

        viewport = new StretchViewport(1024,576, new OrthographicCamera());//
        stage = new Stage(viewport,sb);
        font =MenuScreen.createFont(24,1f,Color.WHITE,0,0,Color.WHITE);
        hintfont=MenuScreen.createFont(12,1f,Color.WHITE,0,0,Color.WHITE);
        region = Renderer.get().atlas.findRegion("interfacex32");
        texture = region.getTexture();
        createDialogWindow();
        createHintLabel();
         // 26-27 символов в строке
        createButton(0, 5*32, 5*32, 2*32, 2*32, Player.Buttons.UP);//
        createButton(1, 2.8f*32,2.75f*32, 2*32,2*32, Player.Buttons.LEFT);//
        createButton(2, 7.2f*32,2.75f*32, 2*32,2*32, Player.Buttons.RIGHT);//
        createButton(3, 5*32,0.5f*32, 2*32,2*32, Player.Buttons.DOWN);//
        createButton(4, 25*32, 2.75f*32, 2*32, 2*32, Player.Buttons.ACT);//
        loadDialogButtonMap();
        for (Buttons buttons: Buttons.values()){
            buttonText.put(buttons,"");
        }
        switchUI(GameController.get().gameMode);
    }
    public void loadDialogButtonMap(){
        DialogButtonMap.put(Buttons.LEFT,DialogBtn1);
        DialogButtonMap.put(Buttons.RIGHT,DialogBtn2);
        DialogButtonMap.put(Buttons.NEXT,DialogBtn3);
    }
    public void createButton(int number, float x, float y, float width, float height, final Player.Buttons signal) {
        // Todo: сделать нормальные кнопки
        Color buttonColor = new Color(1,1,1,0.45f);
        TextureRegion texture = new TextureRegion(this.texture,  region.getRegionX() + 32*buttonWidth * number, region.getRegionY(), 32*buttonWidth, 32*buttonHeight);
        final ImageButton button = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        button.setPosition(x, y);
        button.setSize(width, height);
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
        //stage.setDebugAll(true);
    }
    public void switchUI(GameController.GameMode mode){
        if (mode== GameController.GameMode.DIALOG){
            if (DialogWindow!=null){
                DialogWindow.setVisible(true);
                TextWindow.setVisible(true);
                Hint.setVisible(true);
            }
            for(Button button:buttonMap.values()){
                button.setVisible(false);
                button.setDisabled(true);
            }
        }

        if (mode == GameController.GameMode.PLAYING){
            if (DialogWindow!=null) {
                DialogWindow.setVisible(false);
                TextWindow.setVisible(false);
                Hint.setVisible(false);
            }
            for (Button button:buttonMap.values()){
                button.setVisible(true);
                button.setDisabled(false);
            }
        }
    }
    public void createDialogLabels(Dialog dialog){
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font=font;
        TextWindow = new Label(dialog.getTargetMessage().getText(),labelStyle);
        TextWindow.setBounds(DialogWindow.getX()+27,DialogWindow.getY()-20,DialogWindow.getWidth()-27,DialogWindow.getHeight());
        TextWindow.setAlignment(Align.topLeft);
        TextWindow.setTouchable(Touchable.enabled);
        TextWindow.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if (dialog.nextMessage()){
                    TextWindow.setText(dialog.getTargetMessage().getText());
                }
                else{
                    GameController.get().setGameMode(GameController.GameMode.PLAYING);
                }
                return true;
            }
        });
        stage.addActor(TextWindow);
    }
    private  void createHintLabel(){
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font=hintfont;
        Hint = new Label("Нажмите, чтобы продолжить",labelStyle);
        Hint.setBounds(DialogWindow.getX()+27,DialogWindow.getY()+20,DialogWindow.getWidth()-27,DialogWindow.getHeight()-20);
        Hint.setAlignment(Align.bottomLeft);
        stage.addActor(Hint);
    }
    private void createDialogWindow(){
        TextureRegion textureRegion = new TextureRegion(this.texture,region.getRegionX(),region.getRegionY()+1024, 64*32,32*32 );
        DialogWindow = new Image(textureRegion);
        DialogWindow.setSize(600,200);
        DialogWindow.setPosition(212,20);
        stage.addActor(DialogWindow);
    }



}
