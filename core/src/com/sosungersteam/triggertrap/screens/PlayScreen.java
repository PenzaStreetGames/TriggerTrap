package com.sosungersteam.triggertrap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.sosungersteam.triggertrap.TriggerTrap;


import java.util.Iterator;

public class PlayScreen implements Screen {
    private TriggerTrap game;
    private Music mainmenu;
    Texture somov;
    Texture studentImage;
    Rectangle somovRect;
    Sound sound;
    private Array<Rectangle> students;
    private long lastDropTime;
    private OrthographicCamera camera;
    public PlayScreen(TriggerTrap game){
        this.game=game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        sound = Gdx.audio.newSound(Gdx.files.internal("wilhelm_scream.mp3"));
        createSomov();
        setMusic();
        studentImage = new Texture("student.png");
        students = new Array<Rectangle>();
        spawnStudent();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.begin();
        game.batch.draw(somov, somovRect.x, somovRect.y,
                somovRect.width, somovRect.height, 0, 0,
                somov.getWidth(), somov.getHeight(), false, false);
        SomovMoves();
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnStudent();
        deadStudent();
        for(Rectangle student: students) {
            game.batch.draw(studentImage, student.x, student.y);
        }
        game.batch.end();
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
        game.batch.dispose();
        somov.dispose();
    }
    private void deadStudent(){
        for (Iterator<Rectangle> iter = students.iterator(); iter.hasNext(); ) {
            Rectangle student = iter.next();
            student.y -= 200 * Gdx.graphics.getDeltaTime();
            if(student.y + 64 < 0) iter.remove();
            if(student.overlaps(somovRect)) {
                sound.play();
                iter.remove();
            }
        }
    }
    private void SomovMoves(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) somovRect.y -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) somovRect.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) somovRect.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) somovRect.x += 200 * Gdx.graphics.getDeltaTime();
    }
    private void setMusic(){
        mainmenu = Gdx.audio.newMusic(Gdx.files.internal("Nitro Fun - Cheat Codes.mp3"));
        mainmenu.play();
        mainmenu.setLooping(true);
        mainmenu.setVolume(0.1f);
    }
    private void createSomov(){
        somov = new Texture("somov.png");
        somovRect = new Rectangle();
        somovRect.x = 800 / 2 - 64 / 2;
        somovRect.y = 20;
        somovRect.width = somov.getWidth() * 4;
        somovRect.height = somov.getHeight() * 4;
    }
    private void spawnStudent() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        students.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }
    private void createTouchpad(){

    }
}
