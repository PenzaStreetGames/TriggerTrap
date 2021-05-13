package com.sosungersteam.triggertrap.model.dialogs;

import com.badlogic.gdx.audio.Sound;

public class Message {
    public String text;
    public float speed;
    public Sound sound;
    public int targetChar;
    public boolean wasPrinted;
    public Message(String text){
        this.text = text;
        wasPrinted=false;
    }
    public String getText(){
        wasPrinted=true;
        return text;
    }

}
