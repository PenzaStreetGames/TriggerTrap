package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.dialogs.Dialog;
import com.sosungersteam.triggertrap.model.dialogs.Message;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DialogManager extends AbstractResourceManager implements MapObjectManager<Dialog> {
    public static DialogManager manager = null;
    public Array<Dialog> dialogs;

    private DialogManager() {

    }

    public static DialogManager get() {
        if (manager == null)
            manager = new DialogManager();
        return manager;
    }


    @Override
    public void load() {
        JSONArray list = getElementsArray("dialogs");
        for (int i=0;i<list.size();i++){
            JSONObject element = (JSONObject) list.get(i);
            int id = (int)(long)element.get("id");
            String name = (String)element.get("name");
            Message msg = new Message((String)element.get("messages"));
            dialogs.add(new Dialog(name,id));
        }
    }

    @Override
    public Dialog getById(int id) {
        for (Dialog dialog : dialogs) {
            if (dialog.id == id)
                return dialog;
        }
        return null;
    }
}
