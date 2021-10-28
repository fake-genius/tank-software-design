package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.gameobjects.Tank;
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
    private final TankGraphics tankPlayerGraphics;
    private final ArrayList<TankGraphics> tanksGraphics;
    private final ArrayList<TreeObstacleGraphics> treeObstacleGraphics;

    
    private final Tank playerTank;
    private final ArrayList<TreeObstacle> treeObstacles;
    private final ArrayList<Tank> tanks;

    public LevelRenderer(TiledMap level, TiledMapTileLayer groundLayer, Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks) {
        this.batch = new SpriteBatch();
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = groundLayer;
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        this.blueTankTexture = new Texture("images/tank_blue.png");
        this.tankPlayerGraphics = new TankGraphics(blueTankTexture, this.tileMovement);
        this.greenTreeTexture = new Texture("images/greenTree.png");
        this.treeObstacleGraphics = new ArrayList<>();
        for (var tree : treeObstacles)
            treeObstacleGraphics.add(new TreeObstacleGraphics(greenTreeTexture, this.tileMovement));
        this.tanksGraphics = new ArrayList<>();
        for (var tank : tanks)
            tanksGraphics.add(new TankGraphics(blueTankTexture, this.tileMovement));

        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;
    }

    public TileMovement getTileMovement() {
        return this.tileMovement;
    }

    public void moveRectangleAtTileCenter() {
        for (int i = 0; i < treeObstacles.size(); ++i) {
            var tree = treeObstacles.get(i);
            var treeGraphics = treeObstacleGraphics.get(i);
            GdxGameUtils.moveRectangleAtTileCenter(groundLayer, treeGraphics.getRectangle(), tree.getCoordinates());
        }
    }

    public void render() {
        clearScreen();
        movePlayerRectangle();
        moveTanksRectangle();
        levelRenderer.render();
        batch.begin();
        renderObjects();
        batch.end();
    }

    void movePlayerRectangle() {
        tankPlayerGraphics.moveBetweenTileCenters(playerTank.getCoordinates(), playerTank.getDestinationCoordinates(), playerTank.getMovementProgress());
    }

    void moveTanksRectangle() {
        for (int i = 0; i < tanks.size(); ++i) {
            var tank = tanks.get(i);
            var tankGraphics = tanksGraphics.get(i);
            tankGraphics.moveBetweenTileCenters(tank.getCoordinates(), tank.getDestinationCoordinates(), tank.getMovementProgress());
        }
    }

    void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    void renderObjects() {
        renderPlayer();
        renderTanks();
        renderTrees();
    }

    void renderPlayer() {
        tankPlayerGraphics.render(batch, playerTank.getRotation());
    }

    void renderTanks() {
        for (int i = 0; i < tanksGraphics.size(); ++i) {
            var tank = tanks.get(i);
            var tankGraphics = tanksGraphics.get(i);
            tankGraphics.render(batch, tank.getRotation());
        }
    }

    void renderTrees() {
        for (var treeGraphics : treeObstacleGraphics)
            treeGraphics.render(batch, 0f);
    }

    public void dispose() {
        greenTreeTexture.dispose();
        blueTankTexture.dispose();
        batch.dispose();
    }

}
