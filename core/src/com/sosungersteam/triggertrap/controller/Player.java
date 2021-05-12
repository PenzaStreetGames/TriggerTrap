package com.sosungersteam.triggertrap.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.model.map.InteractiveObject;
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
    public HashMap<Buttons, Boolean> isClicked = new HashMap<>();
    public InteractiveObject targetObject;

    public Player(Person person) {
        for (Buttons button : Buttons.values()) {
            isPressed.put(button, false);
            isClicked.put(button, false);
        }
        this.person = person;
    }

    public void handleInput(float delta){ // testing camera moves
        float vx = 0, vy = 0;
        float velocity_scale = 8*0.5f;

        if (GameController.get().gameMode == GameController.GameMode.PLAYING) {
            if (isPressed.get(Buttons.UP)) {
                person.direction = Person.Direction.UP;
                vy = velocity_scale;
            } else if (isPressed.get(Buttons.RIGHT)) {
                person.direction = Person.Direction.RIGHT;
                vx = velocity_scale;
            } else if (isPressed.get(Buttons.DOWN)) {
                person.direction = Person.Direction.DOWN;
                vy = -velocity_scale;
            } else if (isPressed.get(Buttons.LEFT)) {
                person.direction = Person.Direction.LEFT;
                vx = -velocity_scale;
            }
            if (isClicked.get(Buttons.ACT)) {
                doAction();
                isClicked.put(Buttons.ACT, false);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                vx = velocity_scale;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                vy = velocity_scale;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                vy = -velocity_scale;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                vx = -velocity_scale;
            }
            //System.out.println(person.body.getPosition());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            GameController.get().setGameMode(
                    GameController.get().gameMode == GameController.GameMode.PLAYING ?
                    GameController.GameMode.DIALOG : GameController.GameMode.PLAYING);
            System.out.println(GameController.get().getGameMode());
        }

        person.body.setLinearVelocity(vx, vy);
    }

    public void handleButtons(Buttons button, boolean touched) {
        isPressed.put(button, touched);
    }

    public void singleHandleButtons(Buttons button) {
        isClicked.put(button, true);
    }

    public void setSpawnPoint(SpawnPoint spawnPoint) {
        targetRoomId = spawnPoint.roomId;
        targetRoom = spawnPoint.room;
        this.spawnPoint = spawnPoint;
    }

    public void setTargetObject(InteractiveObject targetObject) {
        this.targetObject = targetObject;
    }

    public void setPerson (Person person) {
        this.person = person;
    }

    public void teleport(SpawnPoint spawnPoint) {
        person.setPersonPosition(spawnPoint.point.x, spawnPoint.point.y);
    }

    public void doAction() {
        if (targetObject != null)
            targetObject.act();
    }
}
