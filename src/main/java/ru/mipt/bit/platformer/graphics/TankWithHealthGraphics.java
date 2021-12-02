package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class TankWithHealthGraphics implements ObjectGraphics {

    private final TankGraphics tankGraphics;
    private final Tank tank;
    private final TextureRegion graphics;
    private Rectangle rectangle;
    private final TileMovement tileMovement;

    private boolean healthOn = false;

    public TankWithHealthGraphics(TankGraphics tankGraphics, Tank tank, Texture healthTexture, TileMovement tileMovement) {
        this.tankGraphics = tankGraphics;
        this.graphics = new TextureRegion(healthTexture);
        this.rectangle = createBoundingRectangle(this.graphics);
        this.tileMovement = tileMovement;
        this.tank = tank;
    }

    @Override
    public void render(Batch batch, float rotation) {
        tankGraphics.render(batch, rotation);
        if (healthOn) {
            graphics.setRegionWidth(tank.getLife());
            drawTextureRegionUnscaled(batch, graphics, rectangle.setCenter(rectangle.getX() + 50f, rectangle.getY() - 50f), 180f);
        }
    }

    @Override
    public void moveBetweenTileCenters(GridPoint2 coordinates, GridPoint2 destinationCoordinates, float movementProgress) {
        if (healthOn) {
            tileMovement.moveRectangleBetweenTileCenters(rectangle, coordinates, destinationCoordinates, movementProgress);
        }
        tankGraphics.moveBetweenTileCenters(coordinates, destinationCoordinates, movementProgress);
    }

    @Override
    public void changeHealthBar() {
        healthOn = !healthOn;
    }
}
