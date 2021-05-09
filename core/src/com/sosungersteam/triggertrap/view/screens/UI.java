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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    private Texture texture;
    private Image DialogWindow;
    private Label DialogText;
    private Dialog dialog;
    public static enum Buttons{LEFT,RIGHT,NEXT};
    private ImageTextButton DialogBtn1;
    private ImageTextButton DialogBtn2;
    private  ImageTextButton DialogBtn3;
    public HashMap<Player.Buttons, Button> buttonMap = new HashMap<>();
    public HashMap<Buttons,Button> DialogButtonMap = new HashMap<>();
    public HashMap<Buttons,String> buttonText = new HashMap<>();
    public static int buttonWidth = 16;
    public static int buttonHeight = 16;
    public TextureAtlas.AtlasRegion region;
    BitmapFont font;

    public UI(SpriteBatch sb){
        viewport = new StretchViewport(1024,576, new OrthographicCamera());//
        stage = new Stage(viewport,sb);
        font =MenuScreen.createFont(8,1f,Color.WHITE,0,0,Color.WHITE);
        region = Renderer.get().atlas.findRegion("interfacex32");
        texture = region.getTexture();
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
        stage.setDebugAll(true);
    }

    public Dialog createDialogWindow(String questText,String btnText1,String btnText2,String btnText3, String DialogText) {
        TextureRegion textureRegion = new TextureRegion(this.texture,region.getRegionX(),region.getRegionY()+32*32,64*32,32*32);
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background=new SpriteDrawable(new Sprite(textureRegion));
        windowStyle.titleFont=font;
        Dialog dialog = new Dialog(questText,windowStyle);
        dialog.setPosition(10.5f*32,1.25f*32);
        dialog.setSize(13*32,4*32);
        createDialogButtonsAndText(dialog,btnText1,btnText2,btnText3,DialogText);
        stage.addActor(dialog);
        return dialog;
    }
    public void createDialogButtonsAndText(Dialog dialog,String text1,String text2,String text3,String textDialog){
        Texture texture = new Texture(Gdx.files.internal("sprites/dialog_button.png"));
        Label.LabelStyle labelStyle= new Label.LabelStyle();
        labelStyle.font=font;
        DialogText = new Label(textDialog,labelStyle);
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(new TextureRegionDrawable(texture),
                new TextureRegionDrawable(texture),new TextureRegionDrawable(texture), font);
        for (Buttons buttons: Buttons.values()){
            createDialogButton(dialog,style,buttons);
        }
        dialog.text(DialogText);
    }
    public void createDialogButton(Dialog dialog, ImageTextButton.ImageTextButtonStyle style,UI.Buttons buttons){
        ImageTextButton button = new ImageTextButton(buttonText.get(buttons),style);
        dialog.button(button);
        DialogButtonMap.put(buttons,button);
    }
    public void setButtonsText(String text1, String text2, String text3){
        buttonText.put(Buttons.LEFT,text1);
        buttonText.put(Buttons.RIGHT,text2);
        buttonText.put(Buttons.NEXT,text3);
    }
    public void switchUI(GameController.GameMode mode){
        if (mode== GameController.GameMode.DIALOG){
            setButtonsText("Да","Нет","Далее");
            dialog = createDialogWindow("Студент","Да","Нет","Далее","Вкусная пересдача у Сомова сегодня будет...");
            for(Button button:buttonMap.values()){
                button.setVisible(false);
                button.setDisabled(true);
            }
        }
        if (mode == GameController.GameMode.PLAYING){
            if (dialog!=null) {
                dialog.remove();
            }
            for (Button button:buttonMap.values()){
                button.setVisible(true);
                button.setDisabled(false);
            }
        }
    }
}
