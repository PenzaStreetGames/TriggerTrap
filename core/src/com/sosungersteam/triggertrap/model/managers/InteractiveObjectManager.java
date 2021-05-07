package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.map.InteractiveObject;

public class InteractiveObjectManager {
    public static InteractiveObjectManager manager = null;

    public Array<InteractiveObject> objects = new Array<>();

    private InteractiveObjectManager() {

    }

    public static InteractiveObjectManager get() {
        if (manager == null)
            manager = new InteractiveObjectManager();
        return manager;
    }

    public void addObject(InteractiveObject object) {
        objects.add(object);
    }

    public InteractiveObject getByName(String name) {
        for (InteractiveObject object : objects.iterator()) {
            if (object.name.equals(name))
                return object;
        }
        return null;
    }
}
