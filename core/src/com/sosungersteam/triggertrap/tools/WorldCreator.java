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

public class WorldCreator { //Как макет комнаты, будет главный класс Мирэа
    public WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        AllStaticObject(world,map,bdef,shape,fdef);
    }
    private void AllStaticObject(World world, TiledMap map, BodyDef bdef, PolygonShape shape, FixtureDef fdef){
        for (int i=0;i<map.getLayers().size();i++){
            createStaticObject(world,map,bdef,shape,fdef,i);
        }
    }
    private void createStaticObject(World world, TiledMap map, BodyDef bdef, PolygonShape shape, FixtureDef fdef, int number){
        Body body;
        for (MapObject object: map.getLayers().get(number).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/1/16f,(rect.getY()+rect.getHeight()/2)/1/16f);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/1/16f,rect.getHeight()/2/1/16f);
            fdef.shape=shape;
            body.createFixture(fdef); // Created Walls, same way for columns
        }
    }

}
