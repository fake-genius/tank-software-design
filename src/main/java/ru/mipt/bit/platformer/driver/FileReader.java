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
        HashMap<GridPoint2, GameObjectType> objectsCoordinates = getObjectsCoordinates(fileData);
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        for (var obj : objectsCoordinates.entrySet()) {
            gameObjects.add(obj.getValue().getObject(obj.getKey()));
        }
        return gameObjects;
    }

    public HashMap<GridPoint2, GameObjectType> getObjectsCoordinates(String fileContent) {
        // предполагается, что в файле нет лишних клеток
        HashMap<GridPoint2, GameObjectType> objectsCoordinates = new HashMap<>();
        int i = 0;
        char symbol;
        int n = fileContent.length();
        int x = 0, y = height - 1;
        GridPoint2 coords, playerCoords = new GridPoint2(1, 1);
        while (i < n) {
            symbol = fileContent.charAt(i);
            if (symbol == '_') {
                x += 1;
            }
            else if (symbol == 'T') {
                coords = new GridPoint2(x, y);
                objectsCoordinates.put(coords, GameObjectType.TREE);
                x += 1;
            }
            else if (symbol == 'X') {
                playerCoords = new GridPoint2(x, y);
                //objectsCoordinates.put(coords, GameObjectType.PLAYER);
                x += 1;
            }
            else if (symbol == '\n') {
                y -= 1;
                x = 0;
            }
            else
                i += 1;
            i += 1;
        }
        objectsCoordinates.put(playerCoords, GameObjectType.PLAYER);
        return objectsCoordinates;
    }
}
