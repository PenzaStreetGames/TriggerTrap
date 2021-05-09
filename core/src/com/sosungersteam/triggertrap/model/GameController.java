package com.sosungersteam.triggertrap.model;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.TriggerTrap;
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
import com.sosungersteam.triggertrap.model.persons.Person;
import com.sosungersteam.triggertrap.model.persons.Somov;
import com.sosungersteam.triggertrap.view.music.DJ;
import com.sosungersteam.triggertrap.view.Renderer;
import com.sosungersteam.triggertrap.view.screens.PlayScreen;

public class GameController {
    private static GameController gameController = null;
    public static enum GameMode{MENU, PLAYING, DIALOG};
    public GameMode gameMode;
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
        gameMode = GameMode.MENU;
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
    public void entryToRoom(){
        if (Renderer.get().playScreen != null)
            Renderer.get().playScreen.dispose();

        TriggerTrap.triggerTrap.renderer.setPlayScreen(new PlayScreen(TriggerTrap.triggerTrap));
        TriggerTrap.triggerTrap.setScreen(Renderer.get().playScreen);
        Renderer.get().createNewWorld(getTargetRoom().tiledMap);

        Person person = new Person(Renderer.get().world, Renderer.get().playScreen, "student");
        player.setPerson(person);
        spawnOnStartPosition();
        Renderer.get().playScreen.entryView(person);

    }

    public void spawnOnStartPosition() {
        player.teleport(player.spawnPoint);
    }

    public Room getTargetRoom() {
        return RoomManager.get().getById(GameController.get().player.targetRoomId);
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
        if (Renderer.get().UI!= null){
            Renderer.get().UI.switchUI(gameMode);
        }
    }

    public GameMode getGameMode() {
        return gameMode;
    }
}
