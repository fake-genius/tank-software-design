package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.driver.Level;
import ru.mipt.bit.platformer.driver.LeverGenerators.FileReader;
import ru.mipt.bit.platformer.driver.GameDriver;
import ru.mipt.bit.platformer.driver.LeverGenerators.ObstaclesGenerator;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;
import ru.mipt.bit.platformer.graphics.LevelRenderer;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

/**
 * Adapter
 */
public class GameDesktopLauncher implements ApplicationListener {

    private TiledMap levelTiledMap;
    private TileMovement tileMovement;
    private LevelRenderer levelRenderer;

    private GameDriver gameDriver;

    private Level level;

    private void generateRandomLevel() {
        ObstaclesGenerator obstaclesGenerator = new ObstaclesGenerator(3, 10);
        level = obstaclesGenerator.generateLevel();
    }

    private void getLevelFromFile() {
        FileReader fileReader = new FileReader("src\\test\\resources\\test_level.txt");
        level = fileReader.generateLevel();
    }

    @Override
    public void create() {
        //generateRandomLevel();
        getLevelFromFile();

        levelTiledMap = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(levelTiledMap);
        levelRenderer = new LevelRenderer(levelTiledMap, groundLayer, level.getPlayerTank(), level.getTreeObstacles(), level.getTanks());
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        gameDriver = new GameDriver(level, new NotRecommendingAI(), levelRenderer);
        level.subscribe(gameDriver);
        level.subscribe(levelRenderer);
        level.subscribe(level.getPlayerTank().getCollisionChecker());

        levelRenderer.moveRectangleAtTileCenter();
    }

    @Override
    public void render() {
        float deltaTime = gameDriver.getDeltaTime();
        gameDriver.generateCommands();
        gameDriver.executeCommands();
        level.updateObjects(deltaTime);
        levelRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        levelTiledMap.dispose();
        levelRenderer.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
