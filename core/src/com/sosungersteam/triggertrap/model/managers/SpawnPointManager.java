package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.map.SpawnPoint;

import java.util.HashMap;

public class SpawnPointManager implements MapObjectManager<SpawnPoint> {
    private static SpawnPointManager spawnPointManager = null;
    public Array<SpawnPoint> spawnPoints = new Array<>();
    public HashMap<Integer, Vector2> spawnCoordinates = new HashMap<>();

    private SpawnPointManager() {

    }

    public static SpawnPointManager get() {
        if (spawnPointManager == null)
            spawnPointManager = new SpawnPointManager();
        return spawnPointManager;
    }


    @Override
    public void load() {
        spawnPoints.add(new SpawnPoint(1, 1, 0));
        spawnPoints.add(new SpawnPoint(2, 1, 1));
        spawnPoints.add(new SpawnPoint(3, 1, 2));
        spawnPoints.add(new SpawnPoint(4, 2, 3));

        setSpawnCoordinates();
    }

    @Override
    public SpawnPoint getById(int id) {
        for (SpawnPoint spawnPoint : spawnPoints) {
            if (spawnPoint.id == id)
                return spawnPoint;
        }
        return null;
    }

    public void setSpawnCoordinates() {
        spawnCoordinates.put(1, new Vector2(16, 12));
        spawnCoordinates.put(2, new Vector2(16, 5));
        spawnCoordinates.put(3, new Vector2(16, 19));
        spawnCoordinates.put(4, new Vector2(14, 4));

        for (int key : spawnCoordinates.keySet()) {
            getById(key).setPoint(spawnCoordinates.get(key));
        }
    }

    public void attachSpawnPointsToDoors() {

    }
}
