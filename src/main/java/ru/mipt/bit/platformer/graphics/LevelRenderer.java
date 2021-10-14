package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.gameobjects.Player;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelRenderer {
    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;
    private final Texture blueTankTexture;
    private final Texture greenTreeTexture;
    public PlayerGraphics playerGraphics;
    private final ArrayList<TreeObstacleGraphics> treeObstacleGraphics;
    
    private final Player player;
    private final ArrayList<TreeObstacle> treeObstacles;

    public LevelRenderer(TiledMap level, TiledMapTileLayer groundLayer, Player player, ArrayList<TreeObstacle> treeObstacles) {
        this.batch = new SpriteBatch();
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        //this.groundLayer = getSingleLayer(level);
        this.groundLayer = groundLayer;
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        this.blueTankTexture = new Texture("images/tank_blue.png");
        this.playerGraphics = new PlayerGraphics(blueTankTexture, this.tileMovement);
        this.greenTreeTexture = new Texture("images/greenTree.png");
        this.treeObstacleGraphics = new ArrayList<>();
        for (var tree : treeObstacles)
            treeObstacleGraphics.add(new TreeObstacleGraphics(greenTreeTexture, this.tileMovement));

        this.player = player;
        this.treeObstacles = treeObstacles;
    }

    public TileMovement getTileMovement() {
        return this.tileMovement;
    }

    public void moveRectangleAtTileCenter() {
        for (int i = 0; i < treeObstacles.size(); ++i) {
            var tree = treeObstacles.get(i);
            var treeGraphics = treeObstacleGraphics.get(i);
            GdxGameUtils.moveRectangleAtTileCenter(groundLayer, treeGraphics.getRectangle(), tree.getTreeObstacleCoordinates());
        }
    }

    public void render() {
        clearScreen();
        movePlayerRectangle();
        levelRenderer.render();
        batch.begin();
        renderObjects();
        batch.end();
    }

    void movePlayerRectangle() {
        playerGraphics.moveBetweenTileCenters(player.getCoordinates(), player.getDestinationCoordinates(), player.getMovementProgress());
    }

    void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    void renderObjects() {
        playerGraphics.render(batch, player.getRotation());
        for (var treeGraphics : treeObstacleGraphics)
            treeGraphics.render(batch, 0f);
    }

    public void dispose() {
        greenTreeTexture.dispose();
        blueTankTexture.dispose();
        batch.dispose();
    }

}
