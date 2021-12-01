package ru.mipt.bit.platformer.observer;

import ru.mipt.bit.platformer.gameobjects.GameObject;

public interface Publisher {
    void subscribe(Subscriber subscriber);

    void unsubscribe(Subscriber subscriber);

    void notifySubs(Event event, GameObject gameObject);
}
