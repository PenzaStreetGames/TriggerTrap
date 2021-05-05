package com.sosungersteam.triggertrap.persons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Person extends Actor {
    Texture texture;
    Body body;
    Rectangle size;
    private Array<Animation<TextureRegion>> animations;
    public int animationNumber;
    public int frameNumber;

}
