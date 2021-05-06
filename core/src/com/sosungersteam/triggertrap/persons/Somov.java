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
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
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
    private float stateTimer;
    private final int WIDTH = 23;
    private final int HEIGHT = 35;

    public Somov(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("somov"));
        this.world = world;

        leisure = Leisure.STANDING;
        direction = Direction.DOWN;

        stateTimer = 0;

        cutAnimations();
        defineSomov();
        setBounds(0,0, WIDTH / 1/16f, HEIGHT / 1/16f);
    }


    protected void cutAnimations() {
        animations.put("walkHor", cutAnimation(8,0.1f, 1,102, width, height));
        animations.put("walkUp", cutAnimation(8,0.1f, 1,137, width, height));
        animations.put("walkDown", cutAnimation(8,0.1f, 1,67, width, height));
        animations.put("stayDown", cutAnimation(8,0.2f, 1,172, width, height));
        animations.put("stayUp", cutAnimation(1, 0.2f, 1, 137, width, height));
        animations.put("stayHor", cutAnimation(8, 0.2f, 1, 207, width, height));
    }

    private Animation<TextureRegion> cutAnimation(int countFrames, float timeDuration, int x, int y, int width, int height) {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i=0;i<countFrames;i++){
            frames.add(new TextureRegion(getTexture(),x + i * width, y, width, height));
        }
        return new Animation(timeDuration,frames);
    }

    public void update(float dt) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        defineState();

        TextureRegion region = null;
        if (leisure == Leisure.MOVING) {
            if (direction == Direction.LEFT || direction == Direction.RIGHT)
                region = animations.get("walkHor").getKeyFrame(stateTimer, true);
            else if (direction == Direction.DOWN)
                region = animations.get("walkDown").getKeyFrame(stateTimer, true);
            else if (direction == Direction.UP)
                region = animations.get("walkUp").getKeyFrame(stateTimer, true);
        }
        else if (leisure == Leisure.STANDING) {
            if (direction == Direction.UP) {
                region = animations.get("stayUp").getKeyFrame(stateTimer, true);
            }
            else if (direction == Direction.DOWN)
                region = animations.get("stayDown").getKeyFrame(stateTimer, true);
            else if (direction == Direction.LEFT || direction == Direction.RIGHT)
                region = animations.get("stayHor").getKeyFrame(stateTimer, true);
        }
        else
            region = animations.get("stayDown").getKeyFrame(stateTimer, true);

        if (direction == Direction.LEFT && !region.isFlipX()) {
            region.flip(true, false);
        }
        else if (direction == Direction.RIGHT && region.isFlipX()) {
            region.flip(true, false);
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public void defineState() {
        if (body.getLinearVelocity().x != 0 || body.getLinearVelocity().y != 0)
            leisure = Leisure.MOVING;
        else
            leisure = Leisure.STANDING;
        if (body.getLinearVelocity().y > 0) {
            direction = Direction.UP;
        }
        else if (body.getLinearVelocity().y < 0) {
            direction = Direction.DOWN;
        }
        else if (body.getLinearVelocity().x > 0) {
            direction = Direction.RIGHT;
        }
        else if (body.getLinearVelocity().x < 0) {
            direction = Direction.LEFT;
        }
    }

    public void defineSomov() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(17, 4); // change position
        bdef.type = BodyDef.BodyType.DynamicBody; // Dynamic or kinetic???
        body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Rectangle rect = new Rectangle(1, 1, 15, 25);
        shape.setAsBox(rect.getWidth()/2/1/16f,rect.getHeight()/2/1/16f,//
                new Vector2(1/1/16f,-5/1/16f), 0);//
        fdef.shape=shape;
        body.createFixture(fdef);
        createSensor(fdef,rect);
    }

    private void createSensor(FixtureDef fdef,Rectangle rect){
        ChainShape bodyTouch = new ChainShape();
        float width = rect.getWidth()/2;
        float height = rect.getHeight()/2;
        Vector2[] chain = new Vector2[] {new Vector2((-2-width)/1/16f,(-6-height)/1/16f),new Vector2((-2-width)/1/16f,(2+height)/1/16f)
                ,new Vector2((3+width)/1/16f,(2+height)/1/16f),new Vector2((3+width)/1/16f,(-6-height)/1/16f),new Vector2((-2-width)/1/16f,(-6-height)/1/16f)};
        bodyTouch.createChain(chain);
        fdef.shape = bodyTouch;
        fdef.isSensor=true;
        body.createFixture(fdef).setUserData("bodyTouch");
    }



}
