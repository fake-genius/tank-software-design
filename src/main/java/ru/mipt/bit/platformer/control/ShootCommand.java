package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class ShootCommand implements Command {

    private final Tank tank;

    public ShootCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        GridPoint2 bulletCoords = getNextCoords();
        Bullet bullet = new Bullet(bulletCoords, tank.getRotation(), tank.getCollisionChecker());

        bullet.checkCollisions(bulletCoords);
        //не доделываю, потому что хочу получить фидбек по последнему дз,
        //может мне надо много чего в моих командах переделывать,
        //и я тут только еще больше накидаю не того и не туда
    }


    private GridPoint2 getNextCoords() {
        GridPoint2 coords = tank.getCoordinates();
        GridPoint2 destCoords;
        float rotation = tank.getRotation();
        //System.out.println("shooting");

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
