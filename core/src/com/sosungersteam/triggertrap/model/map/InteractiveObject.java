package com.sosungersteam.triggertrap.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.model.dialogs.Dialog;

public abstract class InteractiveObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body physicBody;
    protected Body interactiveBody;
    protected Fixture fixture;
    public String name;
    public Dialog dialog;

    public InteractiveObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()/2)/1/16f,(bounds.getY()+bounds.getHeight()/2)/1/16f);//
        physicBody = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth()/2/1/16f,bounds.getHeight()/2/1/16f);///
        fdef.shape=shape;
        fixture = physicBody.createFixture(fdef);
    }
    /*
    // Todo: разобраться с этим
    public InteractiveObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        fdef = new FixtureDef(); //???
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()/2)/1/16f,(bounds.getY()+bounds.getHeight()/2)/1/16f);//
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth()/2/1/16f,bounds.getHeight()/2/1/16f);///
        fdef.shape=shape;
        fdef.isSensor = false;
        fixture = body.createFixture(fdef);
    }
        */
    //TODO: поправить, пропадает при переходе туда-обратно
    public void setSensor(World world, Rectangle rect) {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth()/2)/1/16f,(rect.getY() + rect.getHeight()/2)/1/16f);
        float width = rect.getWidth()/2;
        float height = rect.getHeight()/2;
        polygonShape.setAsBox(rect.getWidth()/2/1/16f,rect.getHeight()/2/1/16f);
        interactiveBody = world.createBody(bdef);
        fdef.shape = polygonShape;
        fdef.isSensor=true;
        interactiveBody.createFixture(fdef).setUserData(this);
    }

    public abstract void onAttach();
    public abstract void onDetach();
    public abstract void act();

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
