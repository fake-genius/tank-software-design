package ru.mipt.bit.platformer.driver;

import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;

public class Level {
    private final int width = 10;
    private final int height = 8;

    private final Tank playerTank;
    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> treeObstacles;

    public Level(Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks) {
        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;
    }

    public void update(float deltaTime) {
        playerTank.live(deltaTime);
        for (Tank tank : tanks) {
            tank.live(deltaTime);
        }
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<TreeObstacle> getTreeObstacles() {
        return treeObstacles;
    }
}
