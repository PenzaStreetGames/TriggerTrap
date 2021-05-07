package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.view.Renderer;

public class Bin extends InteractiveObject {
    public Bin(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void onAttach() {
        Renderer.get().UI.buttonMap.get(Player.Buttons.ACT).setColor(1,1,1,0.75f);
    }
    @Override
    public void onDetach(){
        Renderer.get().UI.buttonMap.get(Player.Buttons.ACT).setColor(1,1,1,0.45f);
    }
}
