package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.Direction;

import static org.junit.jupiter.api.Assertions.*;

class TankTest {
/*
    void setPlayerCoordinates(Tank tank, GridPoint2 coordinates, GridPoint2 destinationCoordinates) {
        tank.setCoordinates(coordinates);
        tank.setDestinationCoordinates(destinationCoordinates);
    }

    @Test
    void testNewCoordinatesUp() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        setPlayerCoordinates(tank, new GridPoint2(1, 2), new GridPoint2(2, 3));
        var coords = tank.getNewCoordinates(Direction.Up);
        Assertions.assertEquals(coords[0], new GridPoint2(1, 3));
        Assertions.assertEquals(coords[1], new GridPoint2(2, 4));
    }

    @Test
    void testNewCoordinatesDown() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        setPlayerCoordinates(tank, new GridPoint2(1, 2), new GridPoint2(2, 3));
        var coords = tank.getNewCoordinates(Direction.Down);
        Assertions.assertEquals(coords[0], new GridPoint2(1, 1));
        Assertions.assertEquals(coords[1], new GridPoint2(2, 2));
    }

    @Test
    void testNewCoordinatesLeft() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        setPlayerCoordinates(tank, new GridPoint2(1, 2), new GridPoint2(2, 3));
        var coords = tank.getNewCoordinates(Direction.Left);
        Assertions.assertEquals(coords[0], new GridPoint2(0, 2));
        Assertions.assertEquals(coords[1], new GridPoint2(1, 3));
    }

    @Test
    void testNewCoordinatesRight() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        setPlayerCoordinates(tank, new GridPoint2(1, 2), new GridPoint2(2, 3));
        var coords = tank.getNewCoordinates(Direction.Right);
        Assertions.assertEquals(coords[0], new GridPoint2(2, 2));
        Assertions.assertEquals(coords[1], new GridPoint2(3, 3));
    }

    @Test
    void isMovementPossible() {
        Tank tank = new Tank(new GridPoint2(1, 1));

        assertTrue(tank.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(2, 3)));
        assertTrue(tank.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(1, 3)));
        assertTrue(tank.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(2, 2)));

        assertFalse(tank.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(1, 2)));
    }
    @Test
    void makeMovement() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        tank.makeMovement(new GridPoint2(1, 2));
        assertEquals(tank.getDestinationCoordinates(), new GridPoint2(1, 2));
        assertEquals(tank.getMovementProgress(), 0f);
    }

    @Test
    void testRotationUp() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        tank.changeRotation(Direction.Up);
        assertEquals(tank.getRotation(), 90f);
    }

    @Test
    void testRotationDown() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        tank.changeRotation(Direction.Down);
        assertEquals(tank.getRotation(), -90f);
    }

    @Test
    void testRotationLeft() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        tank.changeRotation(Direction.Left);
        assertEquals(tank.getRotation(), -180f);
    }

    @Test
    void testRotationRight() {
        Tank tank = new Tank(new GridPoint2(1, 1));
        tank.changeRotation(Direction.Right);
        assertEquals(tank.getRotation(), 0f);
    }

 */
}