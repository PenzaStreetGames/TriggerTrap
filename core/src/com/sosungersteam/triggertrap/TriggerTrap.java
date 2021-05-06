package com.sosungersteam.triggertrap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.screens.PlayScreen;

public class TriggerTrap extends Game {
	public SpriteBatch batch;
	public final int PPM=100;
	public GameController gameController;

	@Override
	public void create () {
		gameController = GameController.get();
		gameController.loadResources();

		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
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
