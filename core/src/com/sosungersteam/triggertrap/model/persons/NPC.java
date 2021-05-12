package com.sosungersteam.triggertrap.model.persons;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.managers.NPCManager;
import com.sosungersteam.triggertrap.model.map.Room;
import com.sosungersteam.triggertrap.model.physics.PhysicsBodyCreator;
import com.sosungersteam.triggertrap.view.Renderer;
import com.sosungersteam.triggertrap.view.screens.PlayScreen;

public class NPC extends Person {

    public Room room;
    public String textureName;
    public Vector2 spawnPosition = new Vector2(0, 0);

    public NPC(World world, PlayScreen screen, String textureName) {
        super(world, screen, textureName);
    }

    public NPC(World world, PlayScreen screen, NPCModel model) {
        super(world, screen, model.textureName);
        setModel(model);
        defineSomov(spawnPosition.x, spawnPosition.y);
    }

    public void update(float dt) {
        super.update(dt);
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setModel(NPCModel model) {
        textureName = model.textureName;
        room = model.room;
        spawnPosition = model.spawnPosition;
        direction = model.direction;
    }

    @Override
    public void defineSomov(float x, float y) {

        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y); // change position
        bdef.type = BodyDef.BodyType.DynamicBody; // Dynamic or kinetic???
        body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Rectangle rect = new Rectangle(1, 1, 15, 25);
        shape.setAsBox(rect.getWidth() / 2 / 1 / 16f,rect.getHeight() / 2 / 1 / 16f,//
                new Vector2(0 / 1 / 16f,-5/1/16f), 0);//
        fdef.shape = shape;
        body.createFixture(fdef);
    }

    // создать человечка
    /*
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
        shape.setAsBox(rect.getWidth() / 2 / 1 / 16f,rect.getHeight() / 2 / 1 / 16f,//
                new Vector2(0 / 1 / 16f,-5/1/16f), 0);//
        fdef.shape = shape;
        body.createFixture(fdef);

        createSensor(fdef,rect);
    }

    private void createSensor(FixtureDef fdef,Rectangle rect){
        ChainShape bodyTouch = new ChainShape();
        float width = rect.getWidth()/2;
        float height = rect.getHeight()/2;
        Vector2[] chain = new Vector2[] {new Vector2((-3-width)/1/16f,(-6-height)/1/16f),new Vector2((-3-width)/1/16f,(2+height)/1/16f)
                ,new Vector2((3+width)/1/16f,(2+height)/1/16f),new Vector2((3+width)/1/16f,(-6-height)/1/16f),new Vector2((-2-width)/1/16f,(-6-height)/1/16f)};
        bodyTouch.createChain(chain);
        fdef.shape = bodyTouch;
        fdef.isSensor=true;
        body.createFixture(fdef).setUserData("bodyTouch");
    }

    */

}
