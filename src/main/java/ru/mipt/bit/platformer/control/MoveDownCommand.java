package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.driver.CollisionChecker;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
import static ru.mipt.bit.platformer.util.GdxGameUtils.decrementedY;

public class MoveDownCommand implements Command {

    private final Tank tank;
    private final CollisionChecker collisionChecker;

    public MoveDownCommand(Tank tank, CollisionChecker collisionChecker) {
        this.tank = tank;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void execute() {
        GridPoint2 newPosition = decrementedY(tank.getCoordinates());
        GridPoint2 newDestinationCoordinates = decrementedY(tank.getDestinationCoordinates());

        if (tank.hasMoved()) {
            if (collisionChecker.checkAllObstacles(newPosition, tank)) {
                tank.makeMovement(newDestinationCoordinates);
            }
            tank.changeRotation(Direction.Down);
        }
    }
}
