package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.gameobjects.GameObject;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
    /*
    @Test
    void readFromFileToString() {
        FileReader fileReader = new FileReader();
        String gameString = fileReader.readFromFileToString("src\\test\\resources\\test_level.txt");
        String actual = "__T_T_____\r\nT_______N_\r\n___X______\r\n______T_T_\r\nT___T_____\r\n_T____N___\r\n_N_______T\r\n___TT_____";
        //               012345678911110123456789111101234567891111012345678911110123456789111101234567891111012345678911110123456789
        assertEquals(gameString, actual);
    }

    void printArray(ArrayList<GameObject> array) {
        int i = 0;
        for (; i < array.size() - 1; ++i) {
            TreeObstacle tree = (TreeObstacle) array.get(i);
            System.out.println("TREE: " + tree.getCoordinates());
        }
        Tank tank = (Tank) array.get(i);
        System.out.println("PLAYER: " + tank.getCoordinates());
        System.out.println();
    }

    @Test
    void getObjectsFromStringTrees() {
        FileReader fileReader = new FileReader();
        String gameString = fileReader.readFromFileToString("src\\test\\resources\\test_level.txt");
        fileReader.getObjectsFromString(gameString);

        ArrayList<TreeObstacle> expectedTrees = new ArrayList<>();
        expectedTrees.add(new TreeObstacle(new GridPoint2(2, 7)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(4, 7)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(0, 6)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(6, 4)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(8, 4)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(0, 3)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(4, 3)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(1, 2)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(9, 1)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(3, 0)));
        expectedTrees.add(new TreeObstacle(new GridPoint2(4, 0)));

        ArrayList<TreeObstacle> actualTrees = fileReader.getTrees();

        assertEquals(expectedTrees.size(), actualTrees.size());
        for (int i = 0; i < expectedTrees.size(); ++i) {
            assertEquals(expectedTrees.get(i).getCoordinates(), actualTrees.get(i).getCoordinates());
        }
    }

    @Test
    void getObjectsFromStringTanks() {
        FileReader fileReader = new FileReader();
        String gameString = fileReader.readFromFileToString("src\\test\\resources\\test_level.txt");
        fileReader.getObjectsFromString(gameString);

        ArrayList<Tank> expectedTanks = new ArrayList<>();
        expectedTanks.add(new Tank(new GridPoint2(8, 6)));
        expectedTanks.add(new Tank(new GridPoint2(6, 2)));
        expectedTanks.add(new Tank(new GridPoint2(1, 1)));
        ArrayList<Tank> actualTanks = fileReader.getTanks();
        assertEquals(expectedTanks.size(), actualTanks.size());
        for (int i = 0; i < expectedTanks.size(); ++i) {
            Tank expectedTank = expectedTanks.get(i);
            Tank actualTank = actualTanks.get(i);
            assertEquals(expectedTank.getCoordinates(), actualTank.getCoordinates());
            assertEquals(expectedTank.getDestinationCoordinates(), actualTank.getDestinationCoordinates());
        }
    }

    @Test
    void getObjectsFromStringPlayer() {
        FileReader fileReader = new FileReader();
        String gameString = fileReader.readFromFileToString("src\\test\\resources\\test_level.txt");
        fileReader.getObjectsFromString(gameString);

        Tank expectedPlayer = new Tank(new GridPoint2(3, 5));
        Tank actualPlayer = fileReader.getPlayer();
        assertEquals(expectedPlayer.getCoordinates(), actualPlayer.getCoordinates());
        assertEquals(expectedPlayer.getDestinationCoordinates(), actualPlayer.getDestinationCoordinates());
    }

     */
}