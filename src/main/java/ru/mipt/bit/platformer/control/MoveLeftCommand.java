package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.driver.CollisionChecker;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.decrementedX;
import static ru.mipt.bit.platformer.util.GdxGameUtils.decrementedY;

public class MoveLeftCommand implements Command {

    private final Tank tank;
    private final CollisionChecker collisionChecker;

    public MoveLeftCommand(Tank tank, CollisionChecker collisionChecker) {
        this.tank = tank;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void execute() {
        GridPoint2 newPosition = decrementedX(tank.getCoordinates());
        GridPoint2 newDestinationCoordinates = decrementedX(tank.getDestinationCoordinates());

        if (tank.hasMoved()) {
            if (collisionChecker.checkAllObstacles(newPosition, tank)) {
                tank.makeMovement(newDestinationCoordinates);
            }
            tank.changeRotation(Direction.Left);
        }
    }
}
