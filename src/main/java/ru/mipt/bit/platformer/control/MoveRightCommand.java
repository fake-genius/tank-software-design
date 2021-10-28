package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedX;

public class MoveRightCommand implements Command {

    private final Tank tank;

    public MoveRightCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        GridPoint2 newPosition = incrementedX(tank.getCoordinates());
        GridPoint2 newDestinationCoordinates = incrementedX(tank.getDestinationCoordinates());

        if (tank.hasMoved()) {
            if (tank.checkCollisions(newPosition)) {
                tank.makeMovement(newDestinationCoordinates);
            }
            tank.changeRotation(Direction.Right);
        }
    }
}
