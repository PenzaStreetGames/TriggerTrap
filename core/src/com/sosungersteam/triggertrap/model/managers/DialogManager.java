package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.dialogs.Dialog;
import com.sosungersteam.triggertrap.model.dialogs.Message;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DialogManager extends AbstractResourceManager implements MapObjectManager<Dialog> {
    public static DialogManager manager = null;
    public Array<Dialog> dialogs = new Array<>();

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
        for (int i = 0; i < list.size(); i++) {
            JSONObject element = (JSONObject) list.get(i);
            int id = (int) (long) element.get("id");
            String name = (String) element.get("name");
            JSONArray messages = (JSONArray) element.get("messages");
            Dialog dialog = new Dialog(name, id);
            for (int j = 0; j < messages.size(); j++) {
                String text = (String) messages.get(j);
                System.out.println(text);
                Message message = new Message(text);
                dialog.addMessage(message);
            }
            dialogs.add(dialog);
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

    public Dialog getByName(String name) {
        for (Dialog dialog : dialogs) {
            if (dialog.name.equals(name))
                return dialog;
        }
        return null;
    }
}
