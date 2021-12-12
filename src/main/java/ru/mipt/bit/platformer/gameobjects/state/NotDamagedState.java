package ru.mipt.bit.platformer.gameobjects.state;

import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.Date;

/**
 * Entity
 */
public class NotDamagedState implements State{
    private final Tank tank;

    public NotDamagedState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public boolean canShoot() {
        long time = new Date().getTime();
        long delta = time - tank.getLastTimeShooting();
        if (delta < 2000)
            return false;
        tank.setLastTimeShooting(time);
        return true;
    }

    @Override
    public void takeDamage(Bullet bullet) {
        int life = tank.getLife() - bullet.getDamage();
        if (life == 66) {
            tank.setState(new MediumDamagedState(tank));
        }
        else if (life == 33) {
            tank.setState(new SevereDamagedState(tank));
        }
    }
}
