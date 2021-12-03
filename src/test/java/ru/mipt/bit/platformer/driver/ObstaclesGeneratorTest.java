package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.driver.LeverGenerators.ObstaclesGenerator;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ObstaclesGeneratorTest {

    @Test
    void testGeneratedObjectsIntersection() {
        ObstaclesGenerator og = new ObstaclesGenerator();
        Tank playerTank = og.generatePlayer();
        var obstacles = og.generateObstacles(5);
        var tanks = og.generateTanks(5);
        TreeSet<GridPoint2> coords = new TreeSet<>(new GridPoint2Comparator());
        coords.add(playerTank.getCoordinates());
        for (var tree : obstacles) {
            assertFalse(coords.contains(tree.getCoordinates()));
            coords.add(tree.getCoordinates());
        }
        for (var tank : tanks) {
            assertFalse(coords.contains(tank.getCoordinates()));
            coords.add(tank.getCoordinates());
        }
    }

    @Test
    void testGenerateTanksIntersection() {
        ObstaclesGenerator og = new ObstaclesGenerator();
        var tanks = og.generateTanks(5);
        TreeSet<GridPoint2> coords = new TreeSet<>(new GridPoint2Comparator());
        assertEquals(tanks.size(), 5);
        for (var tank : tanks) {
            assertFalse(coords.contains(tank.getCoordinates()));
            coords.add(tank.getCoordinates());
        }
    }

    @Test
    void testGenerateTreeObstaclesIntersection() {
        ObstaclesGenerator og = new ObstaclesGenerator();
        var obstacles = og.generateObstacles(5);
        TreeSet<GridPoint2> coords = new TreeSet<>(new GridPoint2Comparator());
        assertEquals(obstacles.size(), 5);
        for (var tree : obstacles) {
            assertFalse(coords.contains(tree.getCoordinates()));
            coords.add(tree.getCoordinates());
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