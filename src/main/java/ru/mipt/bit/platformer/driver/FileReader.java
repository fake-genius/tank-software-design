package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class FileReader {
    private final int width = 10;
    private final int height = 8;
    private final Tank playerTank;
    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> trees;

    public FileReader() {
        playerTank = new Tank(new GridPoint2(1, 1));
        tanks = new ArrayList<>();
        trees = new ArrayList<>();
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
                trees.add(new TreeObstacle(coords));
                x += 1;
            } else if (symbol == 'X') {
                coords = new GridPoint2(x, y);
                playerTank.setCoordinates(coords);
                playerTank.setDestinationCoordinates(coords);
                x += 1;
            } else if (symbol == 'N') {
                coords = new GridPoint2(x, y);
                tanks.add(new Tank(coords));
                x += 1;
            } else if (symbol == '\n') {
                y -= 1;
                x = 0;
            }
            i += 1;
        }
    }
}
