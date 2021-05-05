package com.sosungersteam.triggertrap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sosungersteam.triggertrap.screens.PlayScreen;

public class TriggerTrap extends Game {
	public SpriteBatch batch;
	public static final float unitScale = 1/16f;
	public static final int V_WIDTH=16;
	public static final int V_HEIGHT=16;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
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
