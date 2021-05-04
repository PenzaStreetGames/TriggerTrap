package com.sosungersteam.triggertrap.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.screens.PlayScreen;

import java.awt.geom.RectangularShape;

public class Somov extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion somovStand;

    public Somov(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("somov"));
        this.world=world;
        defineSomov();
        somovStand = new TextureRegion(getTexture(),0,0,23,35);
        setBounds(0,0, 23 / 1/16f, 35 / 1/16f);
        setRegion(somovStand);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineSomov(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(100/ 1/16f,100/1/16f); // change position
        bdef.type=BodyDef.BodyType.DynamicBody; // Dynamic or kinetic???
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Rectangle rect = new Rectangle(1, 1, 15, 25);
        shape.setAsBox(rect.getWidth()/2/1/16f,rect.getHeight()/2/1/16f,
                new Vector2(1/1/16f,-5/1/16f), 0);
        fdef.shape=shape;
        b2body.createFixture(fdef);
    }
}
