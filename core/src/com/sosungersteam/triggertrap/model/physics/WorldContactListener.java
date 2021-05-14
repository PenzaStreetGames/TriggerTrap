package com.sosungersteam.triggertrap.model.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sosungersteam.triggertrap.controller.Player;
import com.sosungersteam.triggertrap.model.GameController;
import com.sosungersteam.triggertrap.model.map.InteractiveObject;
import com.sosungersteam.triggertrap.model.persons.NPC;
import com.sosungersteam.triggertrap.view.Renderer;

public class WorldContactListener implements ContactListener { // Метод отвечающий за взаимодействие объектов в мире
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData() == "bodyTouch" || fixB.getUserData()=="bodyTouch"){
            Fixture bodyTouch = (fixA.getUserData()=="bodyTouch")?fixA:fixB;
            Fixture object = bodyTouch==fixA?fixB:fixA;
            if (object.getUserData()!=null && InteractiveObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveObject) object.getUserData()).onAttach();
            }
            if(object.getUserData()!=null && NPC.class.isAssignableFrom(object.getUserData().getClass())){
                ((NPC)object.getUserData()).onAttach();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData() == "bodyTouch" || fixB.getUserData()=="bodyTouch"){
            Fixture bodyTouch = (fixA.getUserData()=="bodyTouch")?fixA:fixB;
            Fixture object = bodyTouch==fixA?fixB:fixA;
            if (object.getUserData()!=null && InteractiveObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveObject) object.getUserData()).onDetach();
            }
            if(object.getUserData()!=null && NPC.class.isAssignableFrom(object.getUserData().getClass())){
                ((NPC)object.getUserData()).onDetach();
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
