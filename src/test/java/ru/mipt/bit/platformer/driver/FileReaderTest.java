package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.gameobjects.GameObject;
import ru.mipt.bit.platformer.gameobjects.GameObjectType;
import ru.mipt.bit.platformer.gameobjects.Player;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {

    @Test
    void readFromFileToString() {
        FileReader fileReader = new FileReader();
        String gameString = fileReader.readFromFileToString("src\\test\\resources\\test_level.txt");
        String actual = "__T_T_____\r\nT_________\r\n___X______\r\n______T_T_\r\nT___T_____\r\n_T________\r\n_________T\r\n___TT_____";
        //               012345678911110123456789111101234567891111012345678911110123456789111101234567891111012345678911110123456789
        assertEquals(gameString, actual);
    }

    @Test
    void getGameObjectsFromFile() {
        FileReader fileReader = new FileReader();
        ArrayList<GameObject> gameObjects = fileReader.getGameObjectsFromFile("src\\test\\resources\\test_level.txt");

        String gameString = fileReader.readFromFileToString("src\\test\\resources\\test_level.txt");
        var objectsCoords = fileReader.getObjectsCoordinates(gameString);

        ArrayList<GameObject> expectedObjects = new ArrayList<>();
        for (var objects : objectsCoords) {
            for (var obj : objects.entrySet()) {
                if (obj.getValue() == GameObjectType.TREE)
                    expectedObjects.add(new TreeObstacle(obj.getKey()));
                else
                    expectedObjects.add(new Player(obj.getKey()));
            }
        }

        int i = 0;
        for (; i < expectedObjects.size() - 1; ++i) {
            TreeObstacle treeExpected = (TreeObstacle) expectedObjects.get(i);
            TreeObstacle treeActual = (TreeObstacle) gameObjects.get(i);
            assertEquals(treeExpected.getTreeObstacleCoordinates(), treeActual.getTreeObstacleCoordinates());
        }

        Player playerExpected = (Player) expectedObjects.get(i);
        Player playerActual = (Player) gameObjects.get(i);
        assertEquals(playerExpected.getRotation(), playerActual.getRotation());
        assertEquals(playerExpected.getCoordinates(), playerActual.getCoordinates());
        assertEquals(playerExpected.getDestinationCoordinates(), playerActual.getDestinationCoordinates());
    }

    void printArray(ArrayList<GameObject> array) {
        int i = 0;
        for (; i < array.size() - 1; ++i) {
            TreeObstacle tree = (TreeObstacle) array.get(i);
            System.out.println("TREE: " + tree.getTreeObstacleCoordinates());
        }
        Player player = (Player) array.get(i);
        System.out.println("PLAYER: " + player.getCoordinates());
        System.out.println();
    }

    @Test
    void getObjectsCoordinates() {
        FileReader fileReader = new FileReader();
        String gameString = fileReader.readFromFileToString("src\\test\\resources\\test_level.txt");
        var objectsCoords = fileReader.getObjectsCoordinates(gameString);

        HashMap<GridPoint2, GameObjectType> expectedTreesCoords = new HashMap<>();
        expectedTreesCoords.put(new GridPoint2(2, 7), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(4, 7), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(0, 6), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(6, 4), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(8, 4), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(0, 3), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(4, 3), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(1, 2), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(9, 1), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(3, 0), GameObjectType.TREE);
        expectedTreesCoords.put(new GridPoint2(4, 0), GameObjectType.TREE);

        HashMap<GridPoint2, GameObjectType> expectedPlayerCoords = new HashMap<>();
        expectedPlayerCoords.put(new GridPoint2(3, 5), GameObjectType.PLAYER);

        ArrayList<HashMap<GridPoint2, GameObjectType>> objectsCoordsExpected = new ArrayList<>();
        objectsCoordsExpected.add(expectedTreesCoords);
        objectsCoordsExpected.add(expectedPlayerCoords);

        assertEquals(objectsCoordsExpected, objectsCoords);
    }
}