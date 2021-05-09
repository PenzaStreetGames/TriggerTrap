package com.sosungersteam.triggertrap.model.dialogs;

import com.badlogic.gdx.utils.Array;

public class Dialog {
    public Array<Message> messages = new Array<>();
    public int targetMessageIndex;
    public Message targetMessage;
}
