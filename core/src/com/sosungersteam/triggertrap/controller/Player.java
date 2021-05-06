package com.sosungersteam.triggertrap.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.model.map.SpawnPoint;
import com.sosungersteam.triggertrap.model.player.GameProgress;
import com.sosungersteam.triggertrap.persons.Person;

import static com.sosungersteam.triggertrap.model.GameController.SCALE;

public class Player {
    public Person person;
    public Room room;
    public GameProgress gameProgress;
    public float x, y;
    public int targetRoomId;
    public Room targetRoom;
    public SpawnPoint spawnPoint;

    public Player(Person person) {
        this.person = person;
    }

    public void handleInput(float delta){ // testing camera moves
        float vx = 0, vy = 0;
        float velocity_scale = 8*0.5f;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            vx = velocity_scale;
            //TODO: add camera moves
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
