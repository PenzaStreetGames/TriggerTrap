package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.dialogs.Dialog;

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
