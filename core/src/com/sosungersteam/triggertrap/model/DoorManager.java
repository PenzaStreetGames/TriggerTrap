package com.sosungersteam.triggertrap.model;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.map.Door;

public class DoorManager implements MapObjectManager<Door> {
    private static DoorManager doorManager;
    public Array<Door> doors = new Array<>();

    private DoorManager() {

    }

    public static DoorManager get() {
        if (doorManager == null)
            doorManager = new DoorManager();
        return doorManager;
    }

    @Override
    public void load() {
        doors.add(new Door(1, 1));
        doors.add(new Door(2, 1));
    }

    @Override
    public Door getById(int id) {
        for (Door door : doors) {
            if (door.id == id)
                return door;
        }
        return null;
    }

    public void setSpawnPositions() {

    }
}
