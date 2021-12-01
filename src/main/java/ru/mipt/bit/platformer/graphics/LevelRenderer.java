package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.GameObject;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;
import ru.mipt.bit.platformer.observer.Event;
import ru.mipt.bit.platformer.observer.Subscriber;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.HashMap;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelRenderer implements Subscriber {
    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;
    private final Texture blueTankTexture;
    private final Texture greenTreeTexture;
    private final Texture bulletTexture;
    private final TankGraphics tankPlayerGraphics;
    private final HashMap<Tank, TankGraphics> tanksToGraphics;
    private final HashMap<TreeObstacle, TreeObstacleGraphics> treesToGraphics;
    private final HashMap<Bullet, BulletGraphics> bulletsToGraphics;
    
    private final Tank playerTank;
    private final ArrayList<TreeObstacle> treeObstacles;
    private final ArrayList<Tank> tanks;
    private final ArrayList<Bullet> bullets;

    public LevelRenderer(TiledMap level, TiledMapTileLayer groundLayer, Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks, ArrayList<Bullet> bullets) {
        this.batch = new SpriteBatch();
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = groundLayer;
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        this.blueTankTexture = new Texture("images/tank_blue.png");
        this.tankPlayerGraphics = new TankGraphics(blueTankTexture, this.tileMovement);
        this.greenTreeTexture = new Texture("images/greenTree.png");
        this.bulletTexture = new Texture("images/bullet_square.png");
        this.treesToGraphics = new HashMap<>();
        for (var tree : treeObstacles)
            treesToGraphics.put(tree, new TreeObstacleGraphics(greenTreeTexture, this.tileMovement));
        this.tanksToGraphics = new HashMap<>();
        for (var tank : tanks)
            tanksToGraphics.put(tank, new TankGraphics(blueTankTexture, this.tileMovement));
        this.bulletsToGraphics = new HashMap<>();

        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;
        this.bullets = bullets;
    }

    private TileMovement getTileMovement() {
        return this.tileMovement;
    }

    public void moveRectangleAtTileCenter() {
        for (var entry : treesToGraphics.entrySet()) {
            var tree = entry.getKey();
            var treeGraphics = entry.getValue();
            GdxGameUtils.moveRectangleAtTileCenter(groundLayer, treeGraphics.getRectangle(), tree.getCoordinates());
        }
    }

    public void render() {
        clearScreen();
        movePlayerRectangle();
        moveTanksRectangle();
        moveBulletsRectangle();
        levelRenderer.render();
        batch.begin();
        renderObjects();
        batch.end();
    }

    void movePlayerRectangle() {
        tankPlayerGraphics.moveBetweenTileCenters(playerTank.getCoordinates(), playerTank.getDestinationCoordinates(), playerTank.getMovementProgress());
    }

    void moveTanksRectangle() {
        for (var entry : tanksToGraphics.entrySet()) {
            var tank = entry.getKey();
            var tankGraphics = entry.getValue();
            tankGraphics.moveBetweenTileCenters(tank.getCoordinates(), tank.getDestinationCoordinates(), tank.getMovementProgress());
        }
    }

    void moveBulletsRectangle() {
        for (var entry : bulletsToGraphics.entrySet()) {
            var bullet = entry.getKey();
            var bulletGraphics = entry.getValue();
            bulletGraphics.moveBetweenTileCenters(bullet.getCoordinates(), bullet.getDestinationCoordinates(), bullet.getMovementProgress());
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
        renderBullets();
    }

    void renderPlayer() {
        tankPlayerGraphics.render(batch, playerTank.getRotation());
    }

    void renderTanks() {
        for (var entry : tanksToGraphics.entrySet()) {
            entry.getValue().render(batch, entry.getKey().getRotation());
        }
    }

    void renderTrees() {
        for (var entry : treesToGraphics.entrySet())
            entry.getValue().render(batch, 0f);
    }

    void renderBullets() {
        for (var entry : bulletsToGraphics.entrySet())
            entry.getValue().render(batch, 0f);
    }

    public void dispose() {
        greenTreeTexture.dispose();
        blueTankTexture.dispose();
        bulletTexture.dispose();
        batch.dispose();
    }

    @Override
    public void update(Event event, GameObject gameObject) {
        switch(event) {
            case AddBullet:
                bulletsToGraphics.put((Bullet) gameObject, new BulletGraphics(bulletTexture, tileMovement));
                break;
            case RemoveBullet:
                for (var entry : bulletsToGraphics.entrySet()) {
                    if (entry.getKey() == gameObject) {
                        bulletsToGraphics.remove(entry.getKey(), entry.getValue());
                        break;
                    }
                }
                break;
            case RemoveTank:
                for (var entry : tanksToGraphics.entrySet()) {
                    if (entry.getKey() == gameObject) {
                        tanksToGraphics.remove(entry.getKey(), entry.getValue());
                        break;
                    }
                }
                break;
        }
    }
}
