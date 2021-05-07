package com.sosungersteam.triggertrap.model;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.managers.DoorManager;
import com.sosungersteam.triggertrap.model.managers.EdgeManager;
import com.sosungersteam.triggertrap.model.managers.MapObjectManager;
import com.sosungersteam.triggertrap.model.managers.RoomManager;
import com.sosungersteam.triggertrap.model.managers.SpawnPointManager;
import com.sosungersteam.triggertrap.model.map.Door;
import com.sosungersteam.triggertrap.model.map.Edge;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.model.map.SpawnPoint;
import com.sosungersteam.triggertrap.persons.Person;
import com.sosungersteam.triggertrap.view.DJ;
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

    public DJ dj;

    public Array<Person> people;

    private GameController() {
        doorManager = DoorManager.get();
        roomManager = RoomManager.get();
        edgeManager = EdgeManager.get();
        spawnPointManager = SpawnPointManager.get();
        dj = DJ.get();
    }

    public static GameController get() {
        if (gameController == null)
            gameController = new GameController();
        return gameController;
    }

    public void loadResources() {
        dj.load();
        roomManager.load();
        doorManager.load();
        edgeManager.load();
        spawnPointManager.load();
    }

    public void spawnOnStartPosition() {
        player.teleport(player.spawnPoint);
    }

    public Room getTargetRoom() {
        return RoomManager.get().getById(GameController.get().player.targetRoomId);
    }
}
