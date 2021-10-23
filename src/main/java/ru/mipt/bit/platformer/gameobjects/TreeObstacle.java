package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Objects;

public class TreeObstacle implements GameObject {
    private final GridPoint2 treeObstacleCoordinates;

    public TreeObstacle(GridPoint2 coordinates) {
        this.treeObstacleCoordinates = new GridPoint2(coordinates.x, coordinates.y);
    }

    public GridPoint2 getTreeObstacleCoordinates() {
        return this.treeObstacleCoordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof TreeObstacle)) return false;
        if (obj == this) return true;
        return (((TreeObstacle) obj).getTreeObstacleCoordinates() == this.getTreeObstacleCoordinates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(treeObstacleCoordinates);
    }
}
