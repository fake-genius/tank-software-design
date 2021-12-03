package ru.mipt.bit.platformer.AIControl.creators;

import org.awesome.ai.state.immovable.Obstacle;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

/**
 * Adapter
 */
public class ObstacleCreator {

    public Obstacle createObstacle(TreeObstacle treeObstacle) {
        return new Obstacle(treeObstacle.getCoordinates().x, treeObstacle.getCoordinates().y);
    }
}
