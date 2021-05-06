package com.sosungersteam.triggertrap.model;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.map.Room;

import java.util.HashMap;
import java.util.TreeMap;

public class RoomManager implements MapObjectManager<Room> {
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
        // Todo: Добавить загрузку комнат через БД
        roomNames.put("memrea_hall", 1);
        roomNames.put("memrea_under_pants", 2);
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
