package com.sosungersteam.triggertrap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class TriggerTrap extends ApplicationAdapter {
	private OrthographicCamera camera;
	SpriteBatch batch;
	Texture img;
	private Music mainmenu;
	Texture somov;
	Texture studentImage;
	Rectangle somovRect;

	private Array<Rectangle> students;
	private long lastDropTime;

	Texture img;
	private Music mainmenu;

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
		studentImage = new Texture("student.png");

		somovRect = new Rectangle();
		somovRect.x = 800 / 2 - 64 / 2;
		somovRect.y = 20;
		somovRect.width = somov.getWidth() * 4;
		somovRect.height = somov.getHeight() * 4;

		students = new Array<Rectangle>();
		spawnStudent();

		img = new Texture("badlogic.jpg");
		mainmenu = Gdx.audio.newMusic(Gdx.files.internal("Nitro Fun - Cheat Codes.mp3"));
		mainmenu.play();
		mainmenu.setLooping(true);
		mainmenu.setVolume(0.01f);
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
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) somovRect.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) somovRect.y -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) somovRect.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) somovRect.x -= 200 * Gdx.graphics.getDeltaTime();

		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnStudent();

		for (Iterator<Rectangle> iter = students.iterator(); iter.hasNext(); ) {
			Rectangle student = iter.next();
			student.y -= 200 * Gdx.graphics.getDeltaTime();
			if(student.y + 64 < 0) iter.remove();
			if(student.overlaps(somovRect)) {
				.play();
				iter.remove();
			}
		}

		for(Rectangle student: students) {
			batch.draw(studentImage, student.x, student.y);
		}



		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		somov.dispose();
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
}
