package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.Direction;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    void setPlayerCoordinates(Player player, GridPoint2 coordinates, GridPoint2 destinationCoordinates) {
        player.setCoordinates(coordinates);
        player.setDestinationCoordinates(destinationCoordinates);
    }

    @Test
    void testNewCoordinatesUp() {
        Player player = new Player(new GridPoint2(1, 1));
        setPlayerCoordinates(player, new GridPoint2(1, 2), new GridPoint2(2, 3));
        var coords = player.getNewCoordinates(Direction.Up);
        Assertions.assertEquals(coords[0], new GridPoint2(1, 3));
        Assertions.assertEquals(coords[1], new GridPoint2(2, 4));
    }

    @Test
    void testNewCoordinatesDown() {
        Player player = new Player(new GridPoint2(1, 1));
        setPlayerCoordinates(player, new GridPoint2(1, 2), new GridPoint2(2, 3));
        var coords = player.getNewCoordinates(Direction.Down);
        Assertions.assertEquals(coords[0], new GridPoint2(1, 1));
        Assertions.assertEquals(coords[1], new GridPoint2(2, 2));
    }

    @Test
    void testNewCoordinatesLeft() {
        Player player = new Player(new GridPoint2(1, 1));
        setPlayerCoordinates(player, new GridPoint2(1, 2), new GridPoint2(2, 3));
        var coords = player.getNewCoordinates(Direction.Left);
        Assertions.assertEquals(coords[0], new GridPoint2(0, 2));
        Assertions.assertEquals(coords[1], new GridPoint2(1, 3));
    }

    @Test
    void testNewCoordinatesRight() {
        Player player = new Player(new GridPoint2(1, 1));
        setPlayerCoordinates(player, new GridPoint2(1, 2), new GridPoint2(2, 3));
        var coords = player.getNewCoordinates(Direction.Right);
        Assertions.assertEquals(coords[0], new GridPoint2(2, 2));
        Assertions.assertEquals(coords[1], new GridPoint2(3, 3));
    }

    @Test
    void isMovementPossible() {
        Player player = new Player(new GridPoint2(1, 1));

        assertTrue(player.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(2, 3)));
        assertTrue(player.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(1, 3)));
        assertTrue(player.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(2, 2)));

        assertFalse(player.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(1, 2)));
    }
    @Test
    void makeMovement() {
        Player player = new Player(new GridPoint2(1, 1));
        player.makeMovement(new GridPoint2(1, 2));
        assertEquals(player.getDestinationCoordinates(), new GridPoint2(1, 2));
        assertEquals(player.getMovementProgress(), 0f);
    }

    @Test
    void testRotationUp() {
        Player player = new Player(new GridPoint2(1, 1));
        player.changeRotation(Direction.Up);
        assertEquals(player.getRotation(), 90f);
    }

    @Test
    void testRotationDown() {
        Player player = new Player(new GridPoint2(1, 1));
        player.changeRotation(Direction.Down);
        assertEquals(player.getRotation(), -90f);
    }

    @Test
    void testRotationLeft() {
        Player player = new Player(new GridPoint2(1, 1));
        player.changeRotation(Direction.Left);
        assertEquals(player.getRotation(), -180f);
    }

    @Test
    void testRotationRight() {
        Player player = new Player(new GridPoint2(1, 1));
        player.changeRotation(Direction.Right);
        assertEquals(player.getRotation(), 0f);
    }
}