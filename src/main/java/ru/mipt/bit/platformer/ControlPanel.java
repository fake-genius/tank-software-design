package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Input.Keys.*;

public class ControlPanel {
    Direction currentDirection;

    public boolean ifPressedKey() {
        boolean ifPressed = true;
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            currentDirection = Direction.Up;
        }
        else if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            currentDirection = Direction.Left;
        }
        else if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            currentDirection = Direction.Down;
        }
        else if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            currentDirection = Direction.Right;
        }
        else {
            ifPressed = false;
        }
        return ifPressed;
    }
    public Direction getDirectionFromKey() {
        return currentDirection;
    }
}
