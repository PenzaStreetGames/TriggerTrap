package com.sosungersteam.triggertrap.model;

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
        spawnPoints.add(new SpawnPoint(1, 1));

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

        for (int key : spawnCoordinates.keySet()) {
            getById(key).setPoint(spawnCoordinates.get(key));
        }
    }
}
