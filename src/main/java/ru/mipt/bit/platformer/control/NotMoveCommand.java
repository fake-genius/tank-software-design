package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.gameobjects.Tank;

/**
 * Use case
 */
public class NotMoveCommand implements Command {
    private final Tank tank;

    public NotMoveCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {

    }
}
