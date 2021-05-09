package com.sosungersteam.triggertrap.model.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.sosungersteam.triggertrap.model.map.Room;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

public class RoomManager extends AbstractResourceManager implements MapObjectManager<Room> {
    private static RoomManager roomManager;
    public Array<Room> rooms = new Array<>();
    public HashMap<String, Integer> roomNames = new HashMap<>();

    private RoomManager() {}

    public static RoomManager get() {
        if (roomManager == null)
            roomManager = new RoomManager();
        return roomManager;
    }

    @Override
    public void load() {
        JSONArray list = getElementsArray("rooms");
        for (int i = 0; i < list.size(); i++) {
            JSONObject element = (JSONObject) list.get(i);
            int id = (int)(long) element.get("id");
            String name = (String) element.get("name");
            roomNames.put(name, id);
        }
        for (String roomName : roomNames.keySet()) {
            Room room = new Room(roomNames.get(roomName), roomName);
            rooms.add(room);
        }
        setTiledMaps();
    }

    @Override
    public Room getById(int id) {
        for (Room room : rooms) {
            if (room.id == id)
                return room;
        }
        return null;
    }

    public void setTiledMaps() {
        TmxMapLoader maploader = new TmxMapLoader();
        for (Room room : rooms) {
            room.setTiledMap(maploader.load("maps/" + room.name + ".tmx"));
        }
    }
}
