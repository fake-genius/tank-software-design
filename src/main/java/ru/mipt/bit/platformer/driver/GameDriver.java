package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.ControlPanel;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.gameobjects.Player;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;

public class GameDriver {
    private final Player player;
    private final ArrayList<TreeObstacle> treeObstacles;

    public GameDriver(Player player, ArrayList<TreeObstacle> treeObstacles) {
        this.player = player;
        this.treeObstacles = treeObstacles;
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
            if (checkAllObstacles(newPosition)) {
                player.makeMovement(newDestinationCoordinates);
            }
            player.changeRotation(direction);
        }
    }

    boolean checkAllObstacles(GridPoint2 newPosition) {
        for (var tree : treeObstacles) {
            if (!player.isMovementPossible(tree.getTreeObstacleCoordinates(), newPosition))
                return false;
        }
        return true;
    }

    float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
