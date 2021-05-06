package com.sosungersteam.triggertrap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.model.SpawnPointManager;
import com.sosungersteam.triggertrap.persons.Somov;
import com.sosungersteam.triggertrap.screens.PlayScreen;
import com.sosungersteam.triggertrap.view.Renderer;

public class TriggerTrap extends Game {
	public static TriggerTrap triggerTrap;
	public SpriteBatch batch;
	public final int PPM=100;
	public GameController gameController;
	public Renderer renderer;

	@Override
	public void create () {
		triggerTrap = this;

		gameController = GameController.get();
		gameController.loadResources();

		gameController.dj.playMusic("gameChill");
		gameController.dj.setMusicVolume(0.1f);

		batch = new SpriteBatch();
		gameController.player = new Player(null);

		// выбор точки спавна
		gameController.player.setSpawnPoint(SpawnPointManager.get().getById(1));
		//

		entryToRoom();
	}

	public void entryToRoom() {
		if (Renderer.get().playScreen != null)
			Renderer.get().playScreen.dispose();
		renderer = Renderer.get();
		renderer.setPlayScreen(new PlayScreen(this));
		setScreen(Renderer.get().playScreen);
		Renderer.get().createNewWorld(GameController.get().getTargetRoom().tiledMap);

		Somov somov = new Somov(Renderer.get().world, Renderer.get().playScreen);
		GameController.get().player.setPerson(somov);

		gameController.spawnOnStartPosition();
	}

	@Override
	public void resize(int width,int height){
		super.resize(width, height);
	}
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
