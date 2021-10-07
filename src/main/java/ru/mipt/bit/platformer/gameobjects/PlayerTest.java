package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.gameobjects.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    void setPlayerCoordinates(Player player, GridPoint2 coordinates, GridPoint2 destinationCoordinates) {
        player.setCoordinates(coordinates);
        player.setDestinationCoordinates(destinationCoordinates);
    }

    @Test
    void getNewCoordinates() {
        Player player = new Player();
        setPlayerCoordinates(player, new GridPoint2(1, 2), new GridPoint2(2, 3));

        var coords = player.getNewCoordinates(Direction.Up);
        Assertions.assertEquals(coords[0], new GridPoint2(1, 3));
        Assertions.assertEquals(coords[1], new GridPoint2(2, 4));

        coords = player.getNewCoordinates(Direction.Down);
        Assertions.assertEquals(coords[0], new GridPoint2(1, 1));
        Assertions.assertEquals(coords[1], new GridPoint2(2, 2));

        coords = player.getNewCoordinates(Direction.Left);
        Assertions.assertEquals(coords[0], new GridPoint2(0, 2));
        Assertions.assertEquals(coords[1], new GridPoint2(1, 3));

        coords = player.getNewCoordinates(Direction.Right);
        Assertions.assertEquals(coords[0], new GridPoint2(2, 2));
        Assertions.assertEquals(coords[1], new GridPoint2(3, 3));
    }

    @Test
    void isMovementPossible() {
        Player player = new Player();

        assertTrue(player.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(2, 3)));
        assertTrue(player.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(1, 3)));
        assertTrue(player.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(2, 2)));

        assertFalse(player.isMovementPossible(new GridPoint2(1, 2), new GridPoint2(1, 2)));
    }
}