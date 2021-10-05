package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.HashMap;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedX;

public class Player {
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private GridPoint2 coordinates;
    // which tile the player want to go next
    private GridPoint2 destinationCoordinates;
    private float movementProgress = 1f;
    private float rotation;
    private final HashMap<Direction, Float> rotates;

    Player() {
        this.destinationCoordinates = new GridPoint2(1, 1);
        this.coordinates = new GridPoint2(this.destinationCoordinates);
        this.rotation = 0f;
        rotates = new HashMap<>();
        rotates.put(Direction.Up, 90f);
        rotates.put(Direction.Left, -180f);
        rotates.put(Direction.Down, -90f);
        rotates.put(Direction.Right, 0f);
    }

    GridPoint2 getCoordinates() {
        return this.coordinates;
    }
    GridPoint2 getDestinationCoordinates() {
        return this.destinationCoordinates;
    }
    float getMovementProgress() {
        return this.movementProgress;
    }
    float getRotation() {
        return this.rotation;
    }

    boolean hasMoved() {
        return isEqual(this.movementProgress, 1f);
    }

    GridPoint2 makeNewPoint(GridPoint2 point) {
        return new GridPoint2(point.x, point.y);
    }

    GridPoint2[] getNewCoordinates(Direction direction) {
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

    boolean isMovementPossible(GridPoint2 obstacleCoordinates, GridPoint2 newPosition) {
        return !obstacleCoordinates.equals(newPosition);
    }

    void makeMovement(GridPoint2 newDestinationCoordinates) {
        this.destinationCoordinates = makeNewPoint(newDestinationCoordinates);
        this.movementProgress = 0f;
    }

    void changeRotation(Direction direction) {
        this.rotation = rotates.get(direction);
    }

    void movePlayer(GridPoint2 obstacleCoordinates) {
        ControlPanel controlPanel = new ControlPanel();
        if (!controlPanel.ifPressedKey()) {
            return;
        }
        Direction direction = controlPanel.getDirectionFromKey();
        GridPoint2[] newCoordinates = getNewCoordinates(direction);
        GridPoint2 newPosition =  newCoordinates[0];
        GridPoint2 newDestinationCoordinates =  newCoordinates[1];

        if (this.hasMoved()) {
            if (isMovementPossible(obstacleCoordinates, newPosition)) {
                makeMovement(newDestinationCoordinates);
            }
            changeRotation(direction);
        }
    }

    void changeMovementProgress(float deltaTime, float speed) {
        this.movementProgress = continueProgress(this.movementProgress, deltaTime, speed);
    }

    void setCoordinates() {
        this.coordinates.set(this.destinationCoordinates);
    }

    void reachDestination() {
        if (this.hasMoved()) {
            this.setCoordinates();
        }
    }

}
