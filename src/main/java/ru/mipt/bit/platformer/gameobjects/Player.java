package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.Direction;

import java.util.HashMap;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedX;

public class Player {
    private final float MOVEMENT_SPEED = 0.4f;

    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private GridPoint2 coordinates;
    // which tile the player want to go next
    private GridPoint2 destinationCoordinates;
    private float movementProgress = 1f;
    private float rotation;
    private final HashMap<Direction, Float> rotates;

    public Player() {
        this.destinationCoordinates = new GridPoint2(1, 1);
        this.coordinates = new GridPoint2(this.destinationCoordinates);
        this.rotation = 0f;
        rotates = new HashMap<>();
        rotates.put(Direction.Up, 90f);
        rotates.put(Direction.Left, -180f);
        rotates.put(Direction.Down, -90f);
        rotates.put(Direction.Right, 0f);
    }

    public GridPoint2 getCoordinates() {
        return this.coordinates;
    }
    public GridPoint2 getDestinationCoordinates() {
        return this.destinationCoordinates;
    }
    public float getMovementProgress() {
        return this.movementProgress;
    }
    public float getRotation() {
        return this.rotation;
    }

    public boolean hasMoved() {
        return isEqual(this.movementProgress, 1f);
    }

    GridPoint2 makeNewPoint(GridPoint2 point) {
        return new GridPoint2(point.x, point.y);
    }

    public GridPoint2[] getNewCoordinates(Direction direction) {
        GridPoint2 newPosition = makeNewPoint(this.coordinates);
        GridPoint2 newDestinationCoordinates = makeNewPoint(this.destinationCoordinates);
        switch(direction) {
            case Up:
                newPosition = incrementedY(newPosition);
                newDestinationCoordinates.y++;
                break;
            case Left:
                newPosition = decrementedX(newPosition);
                newDestinationCoordinates.x--;
                break;
            case Down:
                newPosition = decrementedY(newPosition);
                newDestinationCoordinates.y--;
                break;
            case Right:
                newPosition = incrementedX(newPosition);
                newDestinationCoordinates.x++;
                break;
        }
        return new GridPoint2[] {newPosition, newDestinationCoordinates};
    }

    public boolean isMovementPossible(GridPoint2 obstacleCoordinates, GridPoint2 newPosition) {
        return !obstacleCoordinates.equals(newPosition);
    }

    public void makeMovement(GridPoint2 newDestinationCoordinates) {
        this.destinationCoordinates = makeNewPoint(newDestinationCoordinates);
        this.movementProgress = 0f;
    }

    public void changeRotation(Direction direction) {
        this.rotation = rotates.get(direction);
    }

    public void changeMovementProgress(float deltaTime) {
        this.movementProgress = continueProgress(this.movementProgress, deltaTime, MOVEMENT_SPEED);
    }

    void setCoordinates() {
        this.coordinates.set(this.destinationCoordinates);
    }

    public void reachDestination() {
        if (this.hasMoved()) {
            this.setCoordinates();
        }
    }

    public float getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public void setDestinationCoordinates(GridPoint2 coordinates) {
        this.destinationCoordinates = coordinates;
    }

}
