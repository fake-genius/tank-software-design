package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.concurrent.ThreadLocalRandom;

import java.util.*;

public class ObstaclesGenerator {
    private final ArrayList<TreeObstacle> obstacles;
    private final ArrayList<Tank> tanks;
    private final int width = 10;
    private final int height = 8;
    private final TreeSet<GridPoint2> takenPoints;

    private GridPoint2 playerCoordinates;
    private ArrayList<GridPoint2> tanksCoordinates;
    private ArrayList<GridPoint2> treeObstaclesCoordinates;

    private final CollisionChecker collisionChecker;

    public ObstaclesGenerator() {
        obstacles = new ArrayList<>();
        tanks = new ArrayList<>();
        takenPoints = new TreeSet<>(new GridPoint2Comparator());

        this.collisionChecker = new CollisionChecker();
    }

    public Tank generatePlayer() {
        int randomWidth = generateNumber(0, width);
        int randomHeight = generateNumber(0, height);
        GridPoint2 coords = new GridPoint2(randomWidth, randomHeight);
        while (takenPoints.contains(coords)) {
            randomWidth = generateNumber(0, width);
            randomHeight = generateNumber(0, height);
            coords = new GridPoint2(randomWidth, randomHeight);
        }
        takenPoints.add(coords);
        //playerCoordinates = coords;
        Tank tank = new Tank(coords, collisionChecker);
        collisionChecker.addMovable(tank);
        return tank;
    }

    public ArrayList<TreeObstacle> generateObstacles(int obstaclesNumber) {
        //treeObstaclesCoordinates = new ArrayList<>();
        int randomWidth = generateNumber(0, width);
        int randomHeight = generateNumber(0, height);
        GridPoint2 coords = new GridPoint2(randomWidth, randomHeight);
        for (int i = 0; i < obstaclesNumber; ++i) {
            while (takenPoints.contains(coords)) {
                randomWidth = generateNumber(0, width);
                randomHeight = generateNumber(0, height);
                coords = new GridPoint2(randomWidth, randomHeight);
            }
            takenPoints.add(coords);
            //treeObstaclesCoordinates.add(coords);
            TreeObstacle treeObstacle = new TreeObstacle(coords);
            collisionChecker.addImmovable(treeObstacle);
            obstacles.add(treeObstacle);
        }
        return obstacles;
    }

    public ArrayList<Tank> generateTanks(int tanksNumber) {
        //tanksCoordinates = new ArrayList<>();
        int randomWidth = generateNumber(0, width);
        int randomHeight = generateNumber(0, height);
        GridPoint2 coords = new GridPoint2(randomWidth, randomHeight);
        for (int i = 0; i < tanksNumber; ++i) {
            while (takenPoints.contains(coords)) {
                randomWidth = generateNumber(0, width);
                randomHeight = generateNumber(0, height);
                coords = new GridPoint2(randomWidth, randomHeight);
            }
            takenPoints.add(coords);
            //tanksCoordinates.add(coords);
            Tank tank = new Tank(coords, collisionChecker);
            collisionChecker.addMovable(tank);
            tanks.add(tank);
        }
        return tanks;
    }

    public int generateNumber(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
