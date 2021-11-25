package ru.mipt.bit.platformer.driver.LeverGenerators;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.driver.CollisionChecker;
import ru.mipt.bit.platformer.driver.Level;
import ru.mipt.bit.platformer.gameobjects.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileReader implements LevelGenerator {
    private final int width = 10;
    private final int height = 8;
    private Tank playerTank;
    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> trees;

    private final CollisionChecker collisionChecker;

    public FileReader() {
        tanks = new ArrayList<>();
        trees = new ArrayList<>();
        collisionChecker = new CollisionChecker();
    }

    public String readFromFileToString(String filePath) {
        try {
            Path fileName = Path.of(filePath);
            return Files.readString(fileName);
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }
    }

    public Tank getPlayer() {
        return playerTank;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<TreeObstacle> getTrees() {
        return trees;
    }

    public void getGameObjectsFromFile(String filePath) {
        String fileData = readFromFileToString(filePath);
        getObjectsFromString(fileData);
    }

    public void getObjectsFromString(String fileContent) {
        // предполагается, что в файле нет лишних клеток
        int i = 0;
        char symbol;
        int n = fileContent.length();
        int x = 0, y = height - 1;
        GridPoint2 coords;
        while (i < n) {
            symbol = fileContent.charAt(i);
            if (symbol == '_') {
                x += 1;
            } else if (symbol == 'T') {
                coords = new GridPoint2(x, y);
                TreeObstacle treeObstacle = new TreeObstacle(coords);
                collisionChecker.addImmovable(treeObstacle);
                trees.add(treeObstacle);
                x += 1;
            } else if (symbol == 'X') {
                coords = new GridPoint2(x, y);
                playerTank = new Tank(coords, collisionChecker);
                collisionChecker.addMovable(playerTank);
                x += 1;
            } else if (symbol == 'N') {
                coords = new GridPoint2(x, y);
                Tank tank = new Tank(coords, collisionChecker);
                collisionChecker.addMovable(tank);
                tanks.add(tank);
                x += 1;
            } else if (symbol == '\n') {
                y -= 1;
                x = 0;
            }
            i += 1;
        }
    }

    @Override
    public Level generateLevel() {
        return new Level(playerTank, trees, tanks);
    }
}
