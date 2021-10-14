package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;

public class TreeObstacle implements GameObject {
    private final GridPoint2 treeObstacleCoordinates;

    public TreeObstacle(GridPoint2 coordinates) {
        this.treeObstacleCoordinates = new GridPoint2(coordinates.x, coordinates.y);
    }

    public GridPoint2 getTreeObstacleCoordinates() {
        return this.treeObstacleCoordinates;
    }
}
