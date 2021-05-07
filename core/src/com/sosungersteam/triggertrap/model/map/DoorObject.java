package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.model.GameController;


public class DoorObject extends InteractiveObjects {
    public Door door;
    public String name;

    public DoorObject(World world, TiledMap map, Rectangle rect, String name) {
        super(world, map, rect);
        fixture.setUserData(this);
        this.name = name;
    }

    @Override
    public void onAttach() {
        if (door.edge != null) {
            GameController.get().player.setSpawnPoint(door.edge.doorInto.spawnPoint);
            TriggerTrap.triggerTrap.entryToRoom();
        }
    }
    @Override
    public void onDetach(){
        return;
    }
}
