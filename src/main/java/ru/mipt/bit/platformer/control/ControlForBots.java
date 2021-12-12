package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.driver.Level;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Use case
 */
public class ControlForBots implements Controller {

    @Override
    public ArrayList<Command> getCommands(ArrayList<Tank> tanks, Level level) {
        ArrayList<Command> commands = new ArrayList<>();
        for (Tank tank : tanks) {
            int num = ThreadLocalRandom.current().nextInt(0, 10000) % 299;
            if (num == 0)
                commands.add(new ShootCommand(tank, level));
            commands.add(getRandomMoveCommand(tank));
        }
        return commands;
    }

    private Command getRandomMoveCommand(Tank tank) {
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
