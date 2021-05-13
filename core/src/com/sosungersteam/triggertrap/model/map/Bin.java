package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.view.Renderer;

public class Bin extends InteractiveObject {
    public Bin(World world, TiledMap map, Rectangle bounds, String name) {
        super(world, map, bounds);
        // fixture.setUserData(this);
        this.name = name;
    }

    @Override
    public void onAttach() {
        Renderer.get().UI.buttonMap.get(Player.Buttons.ACT).setColor(1,1,1,0.75f);
        GameController.get().player.setTargetObject(this);
    }
    @Override
    public void onDetach(){
        Renderer.get().UI.buttonMap.get(Player.Buttons.ACT).setColor(1,1,1,0.45f);
        GameController.get().player.setTargetObject(null);
    }

    @Override
    public void act() {
        Renderer.get().UI.createDialogLabels(dialog);
        GameController.get().setGameMode(GameController.GameMode.DIALOG);
        Renderer.get().UI.switchUI(GameController.get().gameMode);
        System.out.println(name);
    }
}
