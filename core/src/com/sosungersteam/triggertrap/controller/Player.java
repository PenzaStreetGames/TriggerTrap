package com.sosungersteam.triggertrap.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.model.player.GameProgress;
import com.sosungersteam.triggertrap.persons.Person;

public class Player {
    public Person person;
    public Room room;
    public GameProgress gameProgress;
    public float x, y;

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
}
