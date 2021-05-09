package com.sosungersteam.triggertrap.model.managers;

import com.badlogic.gdx.Gdx;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class AbstractResourceManager {
    public JSONArray getElementsArray(String key) {
        String data = Gdx.files.internal("data/" + key + ".json").readString();
        JSONObject object = null;
        try {
            object = (JSONObject) (new JSONParser().parse(data));
        }
        catch (ParseException exception) {
            System.out.println("wrong parse");
        }
        JSONArray list = (JSONArray) object.get(key);
        return list;
    }
}
