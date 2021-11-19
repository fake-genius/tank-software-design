package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.decrementedX;

public class MoveLeftCommand implements Command {

    private final Tank tank;

    public MoveLeftCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        GridPoint2 newPosition = tank.getCoordsByDirection(tank.getCoordinates(), Direction.Left);
        GridPoint2 newDestinationCoordinates = tank.getCoordsByDirection(tank.getDestinationCoordinates(), Direction.Left);
        //GridPoint2 newPosition = decrementedX(tank.getCoordinates());
        //GridPoint2 newDestinationCoordinates = decrementedX(tank.getDestinationCoordinates());

        if (tank.hasMoved()) {
            if (tank.checkCollisions(newPosition)) {
                tank.makeMovement(newDestinationCoordinates);
            }
            tank.changeRotation(Direction.Left);
        }
    }
}
