package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.map.Edge;
import com.sosungersteam.triggertrap.model.map.SpawnPoint;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class SpawnPointManager extends AbstractResourceManager implements MapObjectManager<SpawnPoint> {
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
        JSONArray list = getElementsArray("spawn_points");
        for (int i = 0; i < list.size(); i++) {
            JSONObject element = (JSONObject) list.get(i);
            int id = (int)(long) element.get("id");
            int roomId = (int)(long) element.get("roomId");
            int doorId = (int)(long) element.get("doorId");
            float spawnX = (float)(double) element.get("spawnX");
            float spawnY = (float)(double) element.get("spawnY");
            spawnPoints.add(new SpawnPoint(id, roomId, doorId));
            spawnCoordinates.put(id, new Vector2(spawnX, spawnY));
            getById(id).setPoint(spawnCoordinates.get(id));
        }
    }

    @Override
    public SpawnPoint getById(int id) {
        for (SpawnPoint spawnPoint : spawnPoints) {
            if (spawnPoint.id == id)
                return spawnPoint;
        }
        return null;
    }
}
