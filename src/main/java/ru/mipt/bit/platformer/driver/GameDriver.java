package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.ControlPanel;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.gameobjects.Player;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

public class GameDriver {
    private Player player;
    private TreeObstacle treeObstacle;

    public GameDriver(Player player, TreeObstacle treeObstacle) {
        this.player = player;
        this.treeObstacle = treeObstacle;
    }

    public void movePlayer() {
        float deltaTime = getDeltaTime();

        ControlPanel controlPanel = new ControlPanel();
        if (controlPanel.ifPressedKey()) {
            processMove(controlPanel);
        }
        player.changeMovementProgress(deltaTime);
        player.reachDestination();
    }

    void processMove(ControlPanel controlPanel) {
        Direction direction = controlPanel.getDirectionFromKey();

        GridPoint2[] newCoordinates = player.getNewCoordinates(direction);
        GridPoint2 newPosition = newCoordinates[0];
        GridPoint2 newDestinationCoordinates = newCoordinates[1];

        if (player.hasMoved()) {
            if (player.isMovementPossible(treeObstacle.getTreeObstacleCoordinates(), newPosition)) {
                player.makeMovement(newDestinationCoordinates);
            }
            player.changeRotation(direction);
        }
    }

    float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
