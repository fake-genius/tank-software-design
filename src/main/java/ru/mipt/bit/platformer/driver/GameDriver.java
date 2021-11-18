package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.Gdx;
import org.awesome.ai.AI;
import ru.mipt.bit.platformer.AIControl.TankAIController;
import ru.mipt.bit.platformer.control.ControlForBots;
import ru.mipt.bit.platformer.control.ControlForPlayer;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;

public class GameDriver {
    private final int width = 10;
    private final int height = 8;

    private final Tank playerTank;
    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> treeObstacles;

    private final ControlForPlayer controlForPlayer;
    private final ControlForBots controlForBots;

    private final TankAIController tankAIController;

    public GameDriver(Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks, AI ai) {
        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;

        this.controlForPlayer = new ControlForPlayer();
        this.controlForBots = new ControlForBots();

        this.tankAIController = new TankAIController(ai, playerTank, treeObstacles, tanks, width, height);
    }

    public void moveAll() {
        movePlayer();
        moveTanks();
    }

    public void liveAll(float deltaTime) {
        playerTank.live(deltaTime);
        for (Tank tank : tanks) {
            tank.live(deltaTime);
        }
    }

    public void movePlayer() {
        controlForPlayer.processKey(playerTank).execute();
    }

    public void moveTanks() {
        for (Tank tank : tanks) {
            controlForBots.getRandomCommand(tank).execute();
            //tankAIController.executeCommands();
        }
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
