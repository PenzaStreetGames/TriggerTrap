package com.sosungersteam.triggertrap.model;

public interface MapObjectManager<T> {
    public void load();
    public T getById(int id);
}
