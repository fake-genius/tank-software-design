package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.driver.CollisionChecker;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class NotMoveCommand implements Command {
    private final Tank tank;

    public NotMoveCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {

    }
}
