package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.driver.CollisionChecker;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class NotMoveCommand implements Command {
    private final Tank tank;
    private final CollisionChecker collisionChecker;

    public NotMoveCommand(Tank tank, CollisionChecker collisionChecker) {
        this.tank = tank;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void execute() {

    }
}
