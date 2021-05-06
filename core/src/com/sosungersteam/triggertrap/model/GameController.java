package com.sosungersteam.triggertrap.model;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.map.Door;
import com.sosungersteam.triggertrap.model.map.Edge;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.model.map.SpawnPoint;
import com.sosungersteam.triggertrap.persons.Person;
import com.sosungersteam.triggertrap.view.Renderer;

public class GameController {
    private static GameController gameController = null;

    public static float SCALE = 1/16f;

    public Renderer renderer;
    public Player player;
    public MapObjectManager<Door> doorManager;
    public MapObjectManager<Room> roomManager;
    public MapObjectManager<Edge> edgeManager;
    public MapObjectManager<SpawnPoint> spawnPointManager;
    public Array<Door> doors;
    public Array<Edge> edges;
    public Array<Room> rooms;
    public Array<Person> people;

    private GameController() {
        doorManager = DoorManager.get();
        roomManager = RoomManager.get();
        edgeManager = EdgeManager.get();
        spawnPointManager = SpawnPointManager.get();
    }

    public static GameController get() {
        if (gameController == null)
            gameController = new GameController();
        return gameController;
    }

    public void loadResources() {
        roomManager.load();
        doorManager.load();
        edgeManager.load();
        spawnPointManager.load();
    }

    public void spawnOnStartPosition() {

    }
}
