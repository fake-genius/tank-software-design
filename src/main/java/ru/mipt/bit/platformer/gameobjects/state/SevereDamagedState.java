package ru.mipt.bit.platformer.gameobjects.state;

import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;

/**
 * Entity
 */
public class SevereDamagedState implements State {
    private final Tank tank;

    public SevereDamagedState(Tank tank) {
        this.tank = tank;
        this.tank.setMovementSpeed(tank.getMovementSpeed() * 1.5f);
    }

    @Override
    public boolean canShoot() {
        return false;
    }

    @Override
    public void takeDamage(Bullet bullet) {
        tank.kill();
    }
}
