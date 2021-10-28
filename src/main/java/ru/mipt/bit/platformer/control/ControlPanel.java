package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.driver.CollisionChecker;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static com.badlogic.gdx.Input.Keys.*;

public class ControlPanel {

    public Command processKey(Tank tank, CollisionChecker collisionChecker) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new MoveUpCommand(tank, collisionChecker);
        }
        else if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new MoveLeftCommand(tank, collisionChecker);
        }
        else if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new MoveDownCommand(tank, collisionChecker);
        }
        else if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new MoveRightCommand(tank, collisionChecker);
        }
        return new NotMoveCommand(tank, collisionChecker);
    }

    public Command processRandom(Tank tank, CollisionChecker collisionChecker) {
        Direction direction = Direction.Up;
        direction = direction.getRandomDirection();
        switch (direction) {
            case Up:
                return new MoveUpCommand(tank, collisionChecker);
            case Down:
                return new MoveDownCommand(tank, collisionChecker);
            case Left:
                return new MoveLeftCommand(tank, collisionChecker);
        }
        return new MoveRightCommand(tank, collisionChecker);
    }
}
