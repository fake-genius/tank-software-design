package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class TankGraphics implements ObjectGraphics {
    private final TextureRegion graphics;
    private Rectangle rectangle;
    private final TileMovement tileMovement;

    public TankGraphics(Texture blueTankTexture, TileMovement tileMovement) {
        this.graphics = new TextureRegion(blueTankTexture);
        this.rectangle = createBoundingRectangle(this.graphics);
        this.tileMovement = tileMovement;
    }

    @Override
    public void render(Batch batch, float rotation) {
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, rotation);
    }

    @Override
    public void moveBetweenTileCenters(GridPoint2 coordinates, GridPoint2 destinationCoordinates, float movementProgress) {
        tileMovement.moveRectangleBetweenTileCenters(rectangle, coordinates, destinationCoordinates, movementProgress);
    }

    @Override
    public void changeHealthBar() {

    }

    public TextureRegion getGraphics() {
        return this.graphics;
    }
    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
