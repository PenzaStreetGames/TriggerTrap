package com.sosungersteam.triggertrap.model.managers;

public interface MapObjectManager<T> {
    public void load();
    public T getById(int id);
}
