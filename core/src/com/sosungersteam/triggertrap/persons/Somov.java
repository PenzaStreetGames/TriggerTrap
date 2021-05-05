package com.sosungersteam.triggertrap.persons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.screens.PlayScreen;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class Somov extends Person { // создать человечка
    public enum State {STANDING, RUNNINGHOR, RUNNINGVERUP, RUNNINGVERDOWN}

    public enum Leisure {STANDING, MOVING, TALKING}
    public enum Direction {UP, DOWN, LEFT, RIGHT}

    public Leisure leisure;
    public Direction direction;
    public State currentState;
    public State previousState;
    private Animation<TextureRegion> somovRunHor;
    private Animation<TextureRegion> somovRunVerUP;
    private Animation<TextureRegion> somovRunVerDOWN;
    private Animation<TextureRegion> somovStand;
    private boolean runningPosWay;
    private float stateTimer;
    private final int WIDTH = 23;
    private final int HEIGHT = 35;

    public World world;
    public Body b2body;

    public Somov(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("somov"));
        this.world = world;

        leisure = Leisure.STANDING;
        direction = Direction.DOWN;

        stateTimer = 0;
        runningPosWay = true;

        somovRunHor=newAnimation(8,0.1f,WIDTH,102,WIDTH,HEIGHT);
        somovRunVerUP=newAnimation(8,0.1f,WIDTH,137,WIDTH,HEIGHT);
        somovRunVerDOWN=newAnimation(8,0.1f,WIDTH,67,WIDTH,HEIGHT);
        somovStand=newAnimation(8,0.1f,WIDTH,172,WIDTH,HEIGHT);
        defineSomov();
        setBounds(0,0, WIDTH / 1/16f, HEIGHT / 1/16f);
    }


    private Animation<TextureRegion> newAnimation(int countFrames, float timeDuration, int x, int y, int width, int height) {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i=0;i<countFrames;i++){
            frames.add(new TextureRegion(getTexture(),i*x,y,width,height));
        }
        return new Animation(timeDuration,frames);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case RUNNINGHOR:
                region = somovRunHor.getKeyFrame(stateTimer, true);
                break;
            case RUNNINGVERDOWN:
                region = somovRunVerDOWN.getKeyFrame(stateTimer, true);
                break;
            case RUNNINGVERUP:
                region = somovRunVerUP.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = somovStand.getKeyFrame(stateTimer, true);
                break;
        }
        if ((b2body.getLinearVelocity().x < 0 || !runningPosWay) && !region.isFlipX()) {
            region.flip(true, false);
            runningPosWay = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningPosWay) && region.isFlipX()) {
            region.flip(true, false);
            runningPosWay = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if (b2body.getLinearVelocity().y > 0) {
            return State.RUNNINGVERUP;
        }
        if (b2body.getLinearVelocity().y < 0) {
            return State.RUNNINGVERDOWN;
        }
        if (b2body.getLinearVelocity().x != 0) {
            return State.RUNNINGHOR;
        } else
            return State.STANDING;

    }

    public void defineSomov() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / 1 / 16f, 100 / 1 / 16f); // change position
        bdef.type = BodyDef.BodyType.DynamicBody; // Dynamic or kinetic???
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Rectangle rect = new Rectangle(1, 1, 15, 25);
        shape.setAsBox(rect.getWidth() / 2 / 1 / 16f, rect.getHeight() / 2 / 1 / 16f,
                new Vector2(1 / 1 / 16f, -5 / 1 / 16f), 0);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
