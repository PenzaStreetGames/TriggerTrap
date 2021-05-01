package com.sosungersteam.triggertrap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class TriggerTrap extends ApplicationAdapter {
	private OrthographicCamera camera;
	SpriteBatch batch;
	Texture img;
	private Music mainmenu;
	Texture somov;
	Rectangle somovRect;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		mainmenu = Gdx.audio.newMusic(Gdx.files.internal("Nitro Fun - Cheat Codes.mp3"));
		mainmenu.play();
		mainmenu.setLooping(true);
		mainmenu.setVolume(0.01f);

		somov = new Texture("somov.png");

		somovRect = new Rectangle();
		somovRect.x = 800 / 2 - 64 / 2;
		somovRect.y = 20;
		somovRect.width = somov.getWidth() * 4;
		somovRect.height = somov.getHeight() * 4;
	}

	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(0, 0, 0.2f, 1);
		batch.begin();

		batch.draw(somov, somovRect.x, somovRect.y,
				somovRect.width, somovRect.height, 0, 0,
				somov.getWidth(), somov.getHeight(), false, false);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) somovRect.y -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) somovRect.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) somovRect.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) somovRect.x -= 200 * Gdx.graphics.getDeltaTime();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		somov.dispose();
	}
}
