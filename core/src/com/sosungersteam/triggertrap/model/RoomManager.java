package com.sosungersteam.triggertrap.model;

import com.badlogic.gdx.utils.Array;
import com.sosungersteam.triggertrap.model.map.Room;

public class RoomManager {
    public Array<Room> rooms;

    public void LoadRooms() {
        // Todo: Добавить загрузку комнат через БД
        Room room = new Room();
    }
}
