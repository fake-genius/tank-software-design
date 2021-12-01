package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.driver.CollisionChecker;
import ru.mipt.bit.platformer.driver.Level;
import ru.mipt.bit.platformer.gameobjects.Tank;

import static com.badlogic.gdx.Input.Keys.*;

public class ControlForPlayer {

    public Command processKey(Tank tank, Level level) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new MoveUpCommand(tank);
        }
        else if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new MoveLeftCommand(tank);
        }
        else if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new MoveDownCommand(tank);
        }
        else if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new MoveRightCommand(tank);
        }
        else if (Gdx.input.isKeyPressed(SPACE)) {
            return new ShootCommand(tank, level);
        }
        return new NotMoveCommand(tank);
    }
}
