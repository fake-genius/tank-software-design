package ru.mipt.bit.platformer.observer;

import ru.mipt.bit.platformer.gameobjects.GameObject;

/**
 * Entity
 */
public interface Subscriber {
    void update(Event event, GameObject gameObject);
}
