package com.sosungersteam.triggertrap.model;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.map.Door;
import com.sosungersteam.triggertrap.model.map.Edge;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.view.Renderer;

public class GameController {
    private GameController gameController = null;

    public Renderer renderer;
    public Player player;
    public Array<Door> doors;
    public Array<Edge> edges;
    public Array<Room> rooms;

    private GameController() {}

    GameController get() {
        if (gameController == null)
            gameController = new GameController();
        return gameController;
    }
}
