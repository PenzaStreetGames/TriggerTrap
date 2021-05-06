package com.sosungersteam.triggertrap.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.model.map.Door;

public class WorldCreator { //Как макет комнаты, будет главный класс Мирэа
    public WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        createWalls(world,map,bdef,shape,fdef);
        createDoors(world,map);
    }
    private void createDoors(World world,TiledMap map){
        for (MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect,object.getName());
        }
    }
    private void createWalls(World world, TiledMap map, BodyDef bdef, PolygonShape shape, FixtureDef fdef){
        Body body;
        for (MapObject object: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/1/16f,(rect.getY()+rect.getHeight()/2)/1/16f);//
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/1/16f,rect.getHeight()/2/1/16f);///
            fdef.shape=shape;
            body.createFixture(fdef); // Created Walls, same way for columns
        }
    }

}
