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

public abstract class InteractiveObjects {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    public InteractiveObjects(World world,TiledMap map, Rectangle bounds){
        this.world = world;
        this.map=map;
        this.bounds=bounds;
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()/2)/1/16f,(bounds.getY()+bounds.getHeight()/2)/1/16f);//
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth()/2/1/16f,bounds.getHeight()/2/1/16f);///
        fdef.shape=shape;
        fixture = body.createFixture(fdef);
    }
    public abstract void onAttach();
    public abstract void onDetach();
}
