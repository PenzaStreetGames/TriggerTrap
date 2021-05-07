package com.sosungersteam.triggertrap.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.model.map.SpawnPoint;
import com.sosungersteam.triggertrap.model.player.GameProgress;
import com.sosungersteam.triggertrap.model.persons.Person;

import java.util.HashMap;

public class Player {
    public static enum Buttons {UP, DOWN, LEFT, RIGHT, ACT};
    public Person person;
    public Room room;
    public GameProgress gameProgress;
    public float x, y;
    public int targetRoomId;
    public Room targetRoom;
    public SpawnPoint spawnPoint;
    public ClickListener listener;
    public HashMap<Buttons, Boolean> isPressed = new HashMap<>();

    public Player(Person person) {
        for (Buttons button : Buttons.values()) {
            isPressed.put(button, false);
        }
        this.person = person;
    }

    public void handleInput(float delta){ // testing camera moves
        float vx = 0, vy = 0;
        float velocity_scale = 8*0.5f;

        if (isPressed.get(Buttons.UP)) {
            vy = velocity_scale;
        }
        else if (isPressed.get(Buttons.RIGHT)) {
            vx = velocity_scale;
        }
        else if (isPressed.get(Buttons.DOWN)) {
            vy = -velocity_scale;
        }
        else if (isPressed.get(Buttons.LEFT)) {
            vx = -velocity_scale;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            vx = velocity_scale;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            vy = velocity_scale;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            vy = -velocity_scale;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            vx = -velocity_scale;
        }

        person.body.setLinearVelocity(vx, vy);
    }

    public void handleButtons(Buttons button, boolean touched) {
        isPressed.put(button, touched);
    }

    public void setSpawnPoint(SpawnPoint spawnPoint) {
        targetRoomId = spawnPoint.roomId;
        targetRoom = spawnPoint.room;
        this.spawnPoint = spawnPoint;
    }

    public void setPerson (Person person) {
        this.person = person;
    }

    public void teleport(SpawnPoint spawnPoint) {
        person.setPersonPosition(spawnPoint.point.x, spawnPoint.point.y);
    }
}
