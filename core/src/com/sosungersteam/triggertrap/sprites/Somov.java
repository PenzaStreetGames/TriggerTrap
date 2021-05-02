package com.sosungersteam.triggertrap.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.screens.PlayScreen;

public class Somov{
    public World world;
    public Body b2body;
    private TextureRegion somovStand;
    public Somov(World world, PlayScreen screen) {
        //super(screen.getAtlas().findRegion(""));
        this.world=world;
        defineSomov();
        //somovStand = new TextureRegion(getTexture(),0,0,?,?);
        //setBounds(0,0)
    }
    public void defineSomov(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(100/ TriggerTrap.pixelsMultiplier,100/TriggerTrap.pixelsMultiplier); // change position
        bdef.type=BodyDef.BodyType.DynamicBody; // Dynamic or kinetic???
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/TriggerTrap.pixelsMultiplier); // maybe change radius(need test)
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
