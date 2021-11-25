package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.Gdx;
import org.awesome.ai.AI;
import ru.mipt.bit.platformer.AIControl.TankAIController;
import ru.mipt.bit.platformer.control.Command;
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

    private final ArrayList<Command> commands;

    public GameDriver(Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks, AI ai) {
        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;

        this.controlForPlayer = new ControlForPlayer();
        this.controlForBots = new ControlForBots();

        this.tankAIController = new TankAIController(ai, playerTank, treeObstacles, tanks, width, height);

        this.commands = new ArrayList<>();
    }

    public void generateCommands() {
        generateCommandsPlayer();
        generateCommandsBots();
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
        commands.clear();
    }

    public void generateCommandsPlayer() {
        commands.add(controlForPlayer.processKey(playerTank));
    }

    public void generateCommandsBots() {
        for (Tank tank : tanks) {
            commands.add(controlForBots.getRandomCommand(tank));
            //commands.addAll(tankAIController.getCommands());
        }
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
