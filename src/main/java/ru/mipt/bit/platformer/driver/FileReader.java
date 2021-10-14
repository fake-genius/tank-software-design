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

    public String readFromFileToString(String filePath) {
        try {
            Path fileName = Path.of(filePath);
            return Files.readString(fileName);
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }
    }

    public ArrayList<GameObject> getGameObjectsFromFile(String filePath) {
        String fileData = readFromFileToString(filePath);
        ArrayList<HashMap<GridPoint2, GameObjectType>> objectsCoordinates = getObjectsCoordinates(fileData);
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        for (var objects : objectsCoordinates) {
            for (var obj : objects.entrySet()) {
                gameObjects.add(obj.getValue().getObject(obj.getKey()));
            }
        }
        return gameObjects;
    }

    public ArrayList<HashMap<GridPoint2, GameObjectType>> getObjectsCoordinates(String fileContent) {
        // предполагается, что в файле нет лишних клеток
        HashMap<GridPoint2, GameObjectType> treesCoordinates = new HashMap<>();
        HashMap<GridPoint2, GameObjectType> playerCoordinates = new HashMap<>();
        int i = 0;
        char symbol;
        int n = fileContent.length();
        int x = 0, y = height - 1;
        GridPoint2 coords, playerCoords;
        while (i < n) {
            symbol = fileContent.charAt(i);
            if (symbol == '_') {
                x += 1;
            }
            else if (symbol == 'T') {
                coords = new GridPoint2(x, y);
                treesCoordinates.put(coords, GameObjectType.TREE);
                x += 1;
            }
            else if (symbol == 'X') {
                playerCoords = new GridPoint2(x, y);
                playerCoordinates.put(playerCoords, GameObjectType.PLAYER);
                x += 1;
            }
            else if (symbol == '\n') {
                y -= 1;
                x = 0;
            }
            i += 1;
        }
        ArrayList<HashMap<GridPoint2, GameObjectType>> objects = new ArrayList<>();
        objects.add(treesCoordinates);
        objects.add(playerCoordinates);
        return objects;
    }
}
