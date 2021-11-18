package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.driver.LeverGenerators.FileReader;
import ru.mipt.bit.platformer.driver.GameDriver;
import ru.mipt.bit.platformer.driver.LeverGenerators.ObstaclesGenerator;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;
import ru.mipt.bit.platformer.graphics.LevelRenderer;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private TiledMap level;
    private TileMovement tileMovement;
    private LevelRenderer levelRenderer;

    private Tank playerTank;
    private ArrayList<Tank> tanks;
    private ArrayList<TreeObstacle> treeObstacles;

    private GameDriver gameDriver;

    private void generateRandomLevel() {
        ObstaclesGenerator obstaclesGenerator = new ObstaclesGenerator();
        playerTank = obstaclesGenerator.generatePlayer();
        treeObstacles = obstaclesGenerator.generateObstacles(10);
        tanks = obstaclesGenerator.generateTanks(3);
    }

    private void getLevelFromFile() {
        FileReader fileReader = new FileReader();
        fileReader.getGameObjectsFromFile("src\\test\\resources\\test_level.txt");
        playerTank = fileReader.getPlayer();
        tanks = fileReader.getTanks();
        treeObstacles = fileReader.getTrees();
    }

    @Override
    public void create() {
        //generateRandomLevel();
        getLevelFromFile();

        level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);
        levelRenderer = new LevelRenderer(level, groundLayer, playerTank, treeObstacles, tanks);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        gameDriver = new GameDriver(playerTank, treeObstacles, tanks, new NotRecommendingAI());

        levelRenderer.moveRectangleAtTileCenter();
    }

    @Override
    public void render() {
        float deltaTime = gameDriver.getDeltaTime();
        gameDriver.moveAll();
        gameDriver.liveAll(deltaTime);
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
        level.dispose();
        levelRenderer.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
