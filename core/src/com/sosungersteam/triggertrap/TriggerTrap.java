package com.sosungersteam.triggertrap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TriggerTrap extends ApplicationAdapter {
	OrthographicCamera CAMERA;
	SpriteBatch batch;
	Texture img;
	private Music mainmenu;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		mainmenu = Gdx.audio.newMusic(Gdx.files.internal("Nitro Fun - Cheat Codes.mp3"));
		mainmenu.play();
		mainmenu.setLooping(true);
		mainmenu.setVolume(0.01f);

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
