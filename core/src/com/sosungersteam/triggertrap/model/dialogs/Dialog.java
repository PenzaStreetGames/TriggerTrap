package com.sosungersteam.triggertrap.model.dialogs;

import com.badlogic.gdx.utils.Array;

public class Dialog {
    public Array<Message> messages = new Array<>();
    public Message targetMessage;
    public int messageId;
    public String name;
    public int id;

    public Dialog(String name) {
        this.name = name;
        int messageId = 0;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getTargetMessage() {
        return targetMessage;
    }

    public String getTargetText() {
        return targetMessage.text;
    }

    public boolean nextMessage() {
        if (messageId < messages.size - 1) {
            messageId++;
            targetMessage = messages.get(messageId);
            return true;
        }
        return false;
    }
}
