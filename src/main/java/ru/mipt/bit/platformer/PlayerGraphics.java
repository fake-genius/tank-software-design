package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class PlayerGraphics {
    private final TextureRegion graphics;
    private final Rectangle rectangle;

    PlayerGraphics(Texture blueTankTexture) {
            this.graphics = new TextureRegion(blueTankTexture);
            this.rectangle = createBoundingRectangle(this.graphics);
    }

    TextureRegion getGraphics() {
        return this.graphics;
    }
    Rectangle getRectangle() {
        return this.rectangle;
    }
}
