package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class ControlForBots {
    public Command processRandom(Tank tank) {
        Direction direction = Direction.Up;
        direction = direction.getRandomDirection();
        switch (direction) {
            case Up:
                return new MoveUpCommand(tank);
            case Down:
                return new MoveDownCommand(tank);
            case Left:
                return new MoveLeftCommand(tank);
        }
        return new MoveRightCommand(tank);
    }
}
