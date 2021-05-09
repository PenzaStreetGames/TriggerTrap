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
    private Skin mySkin;
    private Texture texture;
    private ImageButton buttonUp;
    private ImageButton buttonDown;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    private Image DialogWindow;
    private Label DialogText;
    public HashMap<Player.Buttons, Button> buttonMap = new HashMap<>();
    public static int buttonWidth = 16;
    public static int buttonHeight = 16;
    public TextureAtlas.AtlasRegion region;
    BitmapFont font;

    public UI(SpriteBatch sb){
        viewport = new StretchViewport(512,288, new OrthographicCamera());//
        stage = new Stage(viewport,sb);
        font =MenuScreen.createFont(8,1f,Color.WHITE,0,0,Color.WHITE);
        region = Renderer.get().atlas.findRegion("interface");
        texture = region.getTexture();
        Color buttonColor = new Color(1,1,1,0.45f);
        createDialogWindow("Glory Ukraine");
        createButton(0, 40*2, 40*2, 2, 2, Player.Buttons.UP);//
        createButton(1, 2.8f*8*2,2.75f*8*2, 2,2, Player.Buttons.LEFT);//
        createButton(2, 7.2f*8*2,2.75f*8*2, 2,2, Player.Buttons.RIGHT);//
        createButton(3, 5*8*2,0.5f*8*2, 2,2, Player.Buttons.DOWN);//
        createButton(4, 25*8*2, 2.75f*8*2, 2, 2, Player.Buttons.ACT);//


    }

    public void createButton(int number, float x, float y, float width, float height, final Player.Buttons signal) {
        // Todo: сделать нормальные кнопки
        Color buttonColor = new Color(1,1,1,0.45f);
        TextureRegion texture = new TextureRegion(this.texture,  region.getRegionX() + buttonWidth * number, region.getRegionY(), buttonWidth, buttonHeight);
        final ImageButton button = new ImageButton(new SpriteDrawable(new Sprite(texture)));
        button.setPosition(x, y);
        button.setSize(width*16, height*16);
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

    public void createDialogWindow(String questText) {
        System.out.println(region.getRegionX()+" "+ (region.getRegionY()+32));
        TextureRegion textureRegion = new TextureRegion(this.texture,region.getRegionX(),region.getRegionY()+32,64,32);
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background=new SpriteDrawable(new Sprite(textureRegion));
        windowStyle.titleFont=font;
        Dialog dialog = new Dialog("",windowStyle);
        dialog.setPosition(11*8*2,1.25f*8*2);
        dialog.setSize(12*8*2,4*8*2);
        //Label.LabelStyle labelStyle= new Label.LabelStyle();
        //labelStyle.font=font;
        //Label DialogText = new Label(questText,labelStyle);
        //Table t = new Table();

        //DialogText.setPosition(11*8*2,1.25f*8*2);
        //DialogText.setSize(12*8*2,4*8*2);
        //stage.addActor(DialogWindow);
        //stage.addActor(DialogText);
        createDialogButtons(dialog,"Да","Нет","Далее","Сомов ждёт рыбки \nочень долго...");
        stage.addActor(dialog);
    }
    public void createDialogButtons(Dialog dialog,String text1,String text2,String text3,String textDialog){
        Color buttonColor = new Color(1,1,1,0.45f);
        TextureRegion textureRegion = new TextureRegion(this.texture,region.getRegionX(),region.getRegionY()+16,64,16);
        Label.LabelStyle labelStyle= new Label.LabelStyle();
        labelStyle.font=font;
        Label label1 = new Label(textDialog,labelStyle);
        //Label label2 = new Label(text2,labelStyle);
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(new TextureRegionDrawable(textureRegion),
                new TextureRegionDrawable(textureRegion),new TextureRegionDrawable(textureRegion), font);
        final ImageTextButton btn1 = new ImageTextButton(text1,style);
        final ImageTextButton btn2 = new ImageTextButton(text2,style);
        final ImageTextButton btn3 = new ImageTextButton(text3,style);
        dialog.text(label1);
        dialog.row();
        dialog.button(btn1);
        dialog.button(btn2);
        dialog.button(btn3);
        //btn1.setVisible(false);
        //btn2.setVisible(false);

    }
}
