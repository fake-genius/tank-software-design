package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.gameobjects.Player;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ObstaclesGeneratorTest {

    @Test
    void testGeneratedObjects() {
        ObstaclesGenerator og = new ObstaclesGenerator();
        Player player = og.generatePlayer();
        var obstacles = og.generateObstacles(5);
        TreeSet<GridPoint2> coords = new TreeSet<>(new GridPoint2Comparator());
        coords.add(player.getCoordinates());
        for (var tree : obstacles) {
            assertFalse(coords.contains(tree.getTreeObstacleCoordinates()));
            coords.add(tree.getTreeObstacleCoordinates());
        }
    }

    @Test
    void testGenerateObstacles() {
        ObstaclesGenerator og = new ObstaclesGenerator();
        var obstacles = og.generateObstacles(5);
        TreeSet<GridPoint2> coords = new TreeSet<>(new GridPoint2Comparator());
        assertEquals(obstacles.size(), 5);
        for (var tree : obstacles) {
            assertFalse(coords.contains(tree.getTreeObstacleCoordinates()));
            coords.add(tree.getTreeObstacleCoordinates());
        }
    }

    @Test
    void generateNumber() {
        ObstaclesGenerator og = new ObstaclesGenerator();
        int number;
        for (int i = 0; i < 20; ++i) {
            number = og.generateNumber(1, 10);
            assertTrue(number >= 1 & number < 10);
        }
    }
}