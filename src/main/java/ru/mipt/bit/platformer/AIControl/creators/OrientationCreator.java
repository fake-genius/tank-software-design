package ru.mipt.bit.platformer.AIControl.creators;

import com.badlogic.gdx.math.GridPoint2;
import org.awesome.ai.state.movable.Orientation;

public class OrientationCreator {
    public Orientation createOrientation(GridPoint2 coordinates, GridPoint2 destinationCoordinates) {
        int deltaX = destinationCoordinates.x - coordinates.x;
        int deltaY = destinationCoordinates.y - coordinates.y;
        if (deltaX > 0) {
            return Orientation.E;
        } else if (deltaX < 0) {
            return Orientation.W;
        } else if (deltaY > 0) {
            return Orientation.N;
        } else {
            return Orientation.S;
        }
    }
}
