package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.map.Door;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DoorManager extends AbstractResourceManager implements MapObjectManager<Door> {
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
        JSONArray list = getElementsArray("doors");
        for (int i = 0; i < list.size(); i++) {
            JSONObject element = (JSONObject) list.get(i);
            int id = (int)(long) element.get("id");
            int roomId = (int)(long) element.get("roomId");
            doors.add(new Door(id, roomId));
        }
        /*
        doors.add(new Door(1, 1));
        doors.add(new Door(2, 1));
        doors.add(new Door(3, 2));
        doors.add(new Door(4, 2));
        doors.add(new Door(5, 2));
        doors.add(new Door(6, 2));
        doors.add(new Door(7, 2));
         */
    }

    @Override
    public Door getById(int id) {
        for (Door door : doors) {
            if (door.id == id)
                return door;
        }
        return null;
    }
}
