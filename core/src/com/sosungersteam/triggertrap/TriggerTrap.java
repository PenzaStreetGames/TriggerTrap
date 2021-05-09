package com.sosungersteam.triggertrap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.model.managers.SpawnPointManager;
import com.sosungersteam.triggertrap.view.screens.MenuScreen;
import com.sosungersteam.triggertrap.view.screens.UI;
import com.sosungersteam.triggertrap.view.Renderer;

public class TriggerTrap extends Game {
	public static TriggerTrap triggerTrap;
	public SpriteBatch batch;
	public final int PPM=100;
	public GameController gameController;
	public Renderer renderer;
	public static final String bitmapTTF = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\\\/?-+=()*&.;,{}\\\"´`'<>АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

	@Override
	public void create () {
		/*
		triggerTrap = this;
		gameController = GameController.get();
		gameController.loadResources();
		gameController.dj.playMusic("gameChill");
		gameController.dj.setMusicVolume(0.1f);
		renderer = Renderer.get();
		batch = new SpriteBatch();
		renderer.setUI(new UI(batch));
		gameController.player = new Player(null);
		Gdx.input.setInputProcessor(renderer.UI.stage);
		// выбор точки спавна
		gameController.player.setSpawnPoint(SpawnPointManager.get().getById(1));
		//
		gameController.entryToRoom();
		*/
		triggerTrap=this;
		batch=new SpriteBatch();
		gameController = GameController.get();
		gameController.loadResources();
		GameController.get().dj.playMusic("menu");
		renderer = Renderer.get();
		renderer.setMenuScreen(new MenuScreen(batch));
		setScreen(renderer.menuScreen);
	}
	public void gameBegin(){
		GameController.get().setGameMode(GameController.GameMode.PLAYING);
		gameController = GameController.get();

		gameController.dj.playMusic("gameChill");
		gameController.dj.setMusicVolume(0.1f);
		renderer = Renderer.get();
		renderer.setUI(new UI(batch));
		gameController.player = new Player(null);
		Gdx.input.setInputProcessor(renderer.UI.stage);
		// выбор точки спавна
		gameController.player.setSpawnPoint(SpawnPointManager.get().getById(1));
		//
		gameController.entryToRoom();
	}
	/*
	public void entryToRoom() {
		if (Renderer.get().playScreen != null)
			Renderer.get().playScreen.dispose();

		renderer.setPlayScreen(new PlayScreen(this));
		setScreen(Renderer.get().playScreen);
		Renderer.get().createNewWorld(GameController.get().getTargetRoom().tiledMap);

		Somov somov = new Somov(Renderer.get().world, Renderer.get().playScreen);
		GameController.get().player.setPerson(somov);

		gameController.spawnOnStartPosition();
	}*/

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
