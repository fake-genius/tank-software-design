package ru.mipt.bit.platformer.gameobjects.state;

import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;

/**
 * Entity
 */
public interface State {

    boolean canShoot();

    void takeDamage(Bullet bullet);
}
