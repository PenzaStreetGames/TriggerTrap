package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.model.persons.NPC;
import com.sosungersteam.triggertrap.model.persons.NPCModel;
import com.sosungersteam.triggertrap.model.persons.Person;
import com.sosungersteam.triggertrap.view.Renderer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NPCManager extends AbstractResourceManager implements MapObjectManager<NPCModel> {
    public static NPCManager manager = null;
    public Array<NPCModel> people = new Array<>();
    public Array<NPC> peopleInRoom = new Array<>();

    private NPCManager() {

    }

    public static NPCManager get() {
        if (manager == null)
            manager = new NPCManager();
        return manager;
    }

    @Override
    public void load() {
        JSONArray list = getElementsArray("NPC");
        for (int i = 0; i < list.size(); i++) {
            JSONObject element = (JSONObject) list.get(i);
            int id = (int)(long) element.get("id");
            int roomId = (int)(long) element.get("roomId");
            String textureName = (String) element.get("textureName");
            float spawnX = (float)(double) element.get("spawnX");
            float spawnY = (float)(double) element.get("spawnY");
            int dir = (int)(long) element.get("dir");
            Person.Direction direction = Person.Direction.values()[dir];
            people.add(new NPCModel(id, textureName, roomId, spawnX, spawnY, direction));
        }
    }

    @Override
    public NPCModel getById(int id) {
        for (NPCModel model : people) {
            if (model.id == id)
                return model;
        }
        return null;
    }

    public void createPeopleInRoom(Room targetRoom) {
        Renderer renderer = Renderer.get();
        for (NPCModel model : people) {
            if (model.room.id == targetRoom.id) {
                NPC npc = new NPC(renderer.world, renderer.playScreen, model);
                npc.setPosition(model.spawnPosition.x, model.spawnPosition.y);
                npc.setModel(model);
                peopleInRoom.add(npc);
            }
        }
    }

    public void clearPeople() {
        peopleInRoom.clear();
    }

    public void drawPeople(Room targetRoom) {
        for (NPC npc : peopleInRoom) {
            if (npc.room.id == targetRoom.id) {
                npc.draw(TriggerTrap.triggerTrap.batch);
            }
        }
    }

    public void updatePeopleView(Room targetRoom, float dt) {
        for (NPC npc : peopleInRoom) {
            if (npc.room.id == targetRoom.id) {

                npc.update(dt);
            }
        }
    }
}
