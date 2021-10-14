package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.Player;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.concurrent.ThreadLocalRandom;

import java.util.*;

public class ObstaclesGenerator {
    private final ArrayList<TreeObstacle> obstacles;
    private final int width = 10;
    private final int height = 8;
    private final TreeSet<GridPoint2> takenPoints;

    public ObstaclesGenerator() {
        obstacles = new ArrayList<>();
        takenPoints = new TreeSet<>(new GridPoint2Comparator());
    }

    public Player generatePlayer() {
        int randomWidth = generateNumber(0, width);
        int randomHeight = generateNumber(0, height);
        GridPoint2 coords = new GridPoint2(randomWidth, randomHeight);
        while (takenPoints.contains(coords)) {
            randomWidth = generateNumber(0, width);
            randomHeight = generateNumber(0, height);
            coords = new GridPoint2(randomWidth, randomHeight);
        }
        takenPoints.add(coords);
        return new Player(coords);
    }

    public ArrayList<TreeObstacle> generateObstacles(int obstaclesNumber) {
        int randomWidth = generateNumber(0, width);
        int randomHeight = generateNumber(0, height);
        GridPoint2 coords = new GridPoint2(randomWidth, randomHeight);
        for (int i = 0; i < obstaclesNumber; ++i) {
            while (takenPoints.contains(coords)) {
                randomWidth = generateNumber(0, width);
                randomHeight = generateNumber(0, height);
                coords = new GridPoint2(randomWidth, randomHeight);
            }
            System.out.println("generated obstacle at:" + coords);
            takenPoints.add(coords);
            obstacles.add(new TreeObstacle(coords));
        }
        return obstacles;
    }

    public int generateNumber(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
