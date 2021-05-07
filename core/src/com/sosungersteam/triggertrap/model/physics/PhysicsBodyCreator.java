package com.sosungersteam.triggertrap.model.physics;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.view.Renderer;

public class PhysicsBodyCreator {

    static public Body createRectBody(BodyDef bodyDef, FixtureDef fixtureDef, PolygonShape shape) {
        // Todo: вынести создание физических объектов в одну функцию
        return null;
    }
/*
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
    }*/
}
