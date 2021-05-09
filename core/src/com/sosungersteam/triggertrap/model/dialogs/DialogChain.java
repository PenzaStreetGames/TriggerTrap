package com.sosungersteam.triggertrap.model.dialogs;

import com.badlogic.gdx.utils.Array;

public class DialogChain {
    public Array<Dialog> dialogs = new Array<>();
    public int targetDialogIndex;
    public Dialog targetDialog;
}
