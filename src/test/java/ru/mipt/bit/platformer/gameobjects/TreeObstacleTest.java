package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeObstacleTest {
    @Test
    void testCreation() {
        TreeObstacle tree = new TreeObstacle(new GridPoint2(1, 3));
        assertEquals(tree.getTreeObstacleCoordinates(), new GridPoint2(1,3));
    }
}