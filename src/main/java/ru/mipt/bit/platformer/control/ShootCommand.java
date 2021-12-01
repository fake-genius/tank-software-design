package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.driver.Level;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.Date;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class ShootCommand implements Command {

    private final Tank tank;
    private final Level level;

    public ShootCommand(Tank tank, Level level) {
        this.tank = tank;
        this.level = level;
    }

    @Override
    public void execute() {
        if (!tank.canShoot())
            return;

        GridPoint2 bulletCoords = getNextCoords();
        Bullet bullet = new Bullet(bulletCoords, tank.getRotation(), tank.getCollisionChecker(), tank);

        if (bullet.checkCollisions(bulletCoords)) {
            level.addBullet(bullet);
        }
        tank.setLastTimeShooting(new Date().getTime());
    }


    private GridPoint2 getNextCoords() {
        GridPoint2 coords = tank.getCoordinates();
        GridPoint2 destCoords;
        float rotation = tank.getRotation();

        if (rotation == 90.0)
            destCoords = incrementedY(coords);
        else if (rotation == -180.0)
            destCoords = decrementedX(coords);
        else if (rotation == -90.0)
            destCoords = decrementedY(coords);
        else
            destCoords = incrementedX(coords);
        return destCoords;
    }
}
