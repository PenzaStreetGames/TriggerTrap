package com.sosungersteam.triggertrap.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sosungersteam.triggertrap.model.map.InteractiveObjects;

public class WorldContactListener implements ContactListener { // Метод отвечающий за взаимодействие объектов в мире
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData() == "bodyTouch" || fixB.getUserData()=="bodyTouch"){
            Fixture bodyTouch = (fixA.getUserData()=="bodyTouch")?fixA:fixB;
            Fixture object = bodyTouch==fixA?fixB:fixA;
            if (object.getUserData()!=null && InteractiveObjects.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveObjects) object.getUserData()).onUse();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
