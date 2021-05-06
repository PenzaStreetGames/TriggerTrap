package com.sosungersteam.triggertrap.persons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Person extends Sprite {
    Texture texture;
    Body body;
    Rectangle size;
    protected Array<Animation<TextureRegion>> animations = new Array<>();
    public int animationNumber;
    public int frameNumber;
    protected int width = 23;
    protected int height = 35;

    public Person(TextureAtlas.AtlasRegion atlas) {
        super(atlas);
        width = 23;
        height = 35;
    }



}
