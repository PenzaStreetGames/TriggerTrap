package com.sosungersteam.triggertrap.model.dialogs;

import com.badlogic.gdx.utils.Array;

public class Dialog {
    public Array<Message> messages = new Array<>();
    public Message targetMessage;
    public int messageId;
    public String name;
    public int id;

    public Dialog(String name,int id) {
        this.name = name;
        this.id=id;
        int messageId = 0;
        nextMessage();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getTargetMessage() {
        targetMessage = messages.get(messageId);
        return targetMessage;
    }

    public String getTargetText() {
        return targetMessage.text;
    }

    public void setMessageId (int messageId) {
        this.messageId = messageId;
        targetMessage = messages.get(messageId);
    }

    public boolean nextMessage() {
        if (messageId < messages.size - 1) {
            messageId++;
            setMessageId(messageId);
            return true;
        }
        return false;
    }

    public void reset() {
        messageId = 0;
    }
}
