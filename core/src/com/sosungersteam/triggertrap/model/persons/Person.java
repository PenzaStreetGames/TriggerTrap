package com.sosungersteam.triggertrap.model.persons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashMap;

public class Person extends Sprite {
    Texture texture;
    public Body body;
    public World world;
    Rectangle size;
    public int animationNumber;
    public int frameNumber;
    protected int width = 23;
    protected int height = 35;
    protected HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();

    public Person(TextureAtlas.AtlasRegion atlas) {
        super(atlas);
        width = 23;
        height = 35;
    }

    public void setPersonPosition(float x, float y) {
        body.setTransform(x, y, 0);
    }



}
