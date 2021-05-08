package com.sosungersteam.triggertrap.model.physics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sosungersteam.triggertrap.model.managers.DoorManager;
import com.sosungersteam.triggertrap.model.managers.InteractiveObjectManager;
import com.sosungersteam.triggertrap.model.map.Bin;
import com.sosungersteam.triggertrap.model.map.Door;
import com.sosungersteam.triggertrap.model.map.DoorObject;
import com.sosungersteam.triggertrap.model.map.InteractiveObject;

public class WorldCreator { //Как макет комнаты, будет главный класс Мирэа
    public WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        createWalls(world,map,bdef,shape,fdef);
        createDoors(world,map);
        createOtherObjects(world,map);
        createInteractiveObjects(world,map);
    }
    private void createDoors(World world, TiledMap map){
        for (MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            DoorObject doorObject = new DoorObject(world, map, rect, object.getName());

            DoorManager manager = DoorManager.get();
            int number = Integer.parseInt(doorObject.name);
            Door door = manager.getById(number);
            door.attachDoorObject(doorObject);
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

    private void createOtherObjects(World world, TiledMap map){
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            Bin bin = new Bin(world,map,rect, object.getName());
            InteractiveObjectManager.get().addObject(bin);
        }
    }

    // Todo: досоздать сенсоры
    private void createInteractiveObjects(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            InteractiveObjectManager.get().getByName(object.getName()).setSensor(world, rect);
        }
    }

}
