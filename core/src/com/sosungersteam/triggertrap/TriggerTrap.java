package com.sosungersteam.triggertrap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sosungersteam.triggertrap.screens.PlayScreen;

public class TriggerTrap extends Game {
	public SpriteBatch batch;
	public final int PPM=100;
	@Override
	public void create () {
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
