package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.control.ControlPanel;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;

public class GameDriver {
    private final int width = 10;
    private final int height = 8;

    private final Tank playerTank;
    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> treeObstacles;

    private final CollisionChecker collisionChecker;

    private final ControlPanel controlPanel;


    public GameDriver(Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks) {
        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;

        this.collisionChecker = new CollisionChecker(this.playerTank, this.treeObstacles, this.tanks);

        this.controlPanel = new ControlPanel();
    }

    public void moveAll() {
        movePlayer();
        moveTanks();
    }

    public void movePlayer() {
        float deltaTime = getDeltaTime();
        controlPanel.processKey(playerTank, collisionChecker).execute();
        playerTank.changeMovementProgress(deltaTime);
        playerTank.reachDestination();
    }

    public void moveTanks() {
        float deltaTime = getDeltaTime();
        for (Tank tank : tanks) {
            controlPanel.processRandom(tank, collisionChecker).execute();
        }
        for (Tank tank : tanks) {
            tank.changeMovementProgress(deltaTime);
            tank.reachDestination();
        }
    }

    float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
