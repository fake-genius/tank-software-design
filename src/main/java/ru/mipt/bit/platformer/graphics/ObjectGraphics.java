package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public interface ObjectGraphics {
    void render(Batch batch, float rotation);

    void moveBetweenTileCenters(GridPoint2 coordinates, GridPoint2 destinationCoordinates, float movementProgress);

    void changeHealthBar();
}
