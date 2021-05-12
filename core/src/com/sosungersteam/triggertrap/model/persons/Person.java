package com.sosungersteam.triggertrap.model.persons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.view.screens.PlayScreen;

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

    public enum State {STANDING, RUNNINGHOR, RUNNINGVERUP, RUNNINGVERDOWN}

    public enum Leisure {STANDING, MOVING, TALKING}
    public enum Direction {UP, DOWN, LEFT, RIGHT}

    public Person.Leisure leisure;
    public Person.Direction direction;
    public Person.State currentState;
    public Person.State previousState;
    private float stateTimer;
    private final int WIDTH = 23;
    private final int HEIGHT = 35;

    public Person(World world, PlayScreen screen, String textureName) {
        super(screen.getAtlas().findRegion(textureName));
        this.world = world;

        leisure = Person.Leisure.STANDING;
        direction = Person.Direction.DOWN;

        stateTimer = 0;

        cutAnimations();
        defineSomov();
        setBounds(0,0, WIDTH / 1/16f, HEIGHT / 1/16f);
    }

    public void setPersonPosition(float x, float y) {
        body.setTransform(x, y, 0);
    }

    protected void cutAnimations() {
        animations.put("walkHor", cutAnimation(8,0.1f, super.getRegionX(),super.getRegionY() + height, width, height));
        animations.put("walkUp", cutAnimation(8,0.1f, super.getRegionX(),super.getRegionY() + height * 2, width, height));
        animations.put("walkDown", cutAnimation(8,0.1f, super.getRegionX(), super.getRegionY(), width, height));
        animations.put("stayDown", cutAnimation(8,0.2f, super.getRegionX(),super.getRegionY() + height * 3, width, height));
        animations.put("stayUp", cutAnimation(1, 0.2f, super.getRegionX(), super.getRegionY() + height * 2, width, height));
        animations.put("stayHor", cutAnimation(8, 0.2f, super.getRegionX(), super.getRegionY() + height * 4, width, height));
    }

    protected Animation<TextureRegion> cutAnimation(int countFrames, float timeDuration, int x, int y, int width, int height) {
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
        if (leisure == Person.Leisure.MOVING) {
            if (direction == Person.Direction.LEFT || direction == Person.Direction.RIGHT)
                region = animations.get("walkHor").getKeyFrame(stateTimer, true);
            else if (direction == Person.Direction.DOWN)
                region = animations.get("walkDown").getKeyFrame(stateTimer, true);
            else if (direction == Person.Direction.UP)
                region = animations.get("walkUp").getKeyFrame(stateTimer, true);
        }
        else if (leisure == Person.Leisure.STANDING) {
            if (direction == Person.Direction.UP) {
                region = animations.get("stayUp").getKeyFrame(stateTimer, true);
            }
            else if (direction == Person.Direction.DOWN)
                region = animations.get("stayDown").getKeyFrame(stateTimer, true);
            else if (direction == Person.Direction.LEFT || direction == Person.Direction.RIGHT)
                region = animations.get("stayHor").getKeyFrame(stateTimer, true);
        }
        else
            region = animations.get("stayDown").getKeyFrame(stateTimer, true);

        if (direction == Person.Direction.LEFT && !region.isFlipX()) {
            region.flip(true, false);
        }
        else if (direction == Person.Direction.RIGHT && region.isFlipX()) {
            region.flip(true, false);
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public void defineState() {
        if (body.getLinearVelocity().x != 0 || body.getLinearVelocity().y != 0)
            leisure = Person.Leisure.MOVING;
        else
            leisure = Person.Leisure.STANDING;
        if (body.getLinearVelocity().y > 0) {
            direction = Person.Direction.UP;
        }
        else if (body.getLinearVelocity().y < 0) {
            direction = Person.Direction.DOWN;
        }
        else if (body.getLinearVelocity().x > 0) {
            direction = Person.Direction.RIGHT;
        }
        else if (body.getLinearVelocity().x < 0) {
            direction = Person.Direction.LEFT;
        }
    }

    public void  defineSomov() {

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
}
