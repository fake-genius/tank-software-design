package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.Gdx;
import org.awesome.ai.AI;
import ru.mipt.bit.platformer.AIControl.TankAIController;
import ru.mipt.bit.platformer.control.Command;
import ru.mipt.bit.platformer.control.ControlForBots;
import ru.mipt.bit.platformer.control.ControlForPlayer;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.GameObject;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;
import ru.mipt.bit.platformer.observer.Event;
import ru.mipt.bit.platformer.observer.Subscriber;

import java.util.ArrayList;

public class GameDriver implements Subscriber {
    private final int width = 10;
    private final int height = 8;

    private final Tank playerTank;
    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> treeObstacles;
    //private ArrayList<Bullet> bullets;

    private final ControlForPlayer controlForPlayer;
    private final ControlForBots controlForBots;
    private final TankAIController tankAIController;
    private final Level level;

    private final ArrayList<Command> commands;

    public GameDriver(Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks, ArrayList<Bullet> bullets, Level level, AI ai) {
        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;
        //this.bullets = bullets;

        this.controlForPlayer = new ControlForPlayer();
        this.controlForBots = new ControlForBots();
        this.tankAIController = new TankAIController(ai, playerTank, treeObstacles, tanks, width, height);

        this.commands = new ArrayList<>();
        this.level = level;
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
        commands.add(controlForPlayer.processKey(playerTank, level));
    }

    public void generateCommandsBots() {
        for (Tank tank : tanks) {
            commands.add(controlForBots.getRandomCommand(tank, level));
            //commands.addAll(tankAIController.getCommands());
        }
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    @Override
    public void update(Event event, GameObject gameObject) {
        if (event == Event.RemoveTank) {
            tanks.remove((Tank) gameObject);
        }
    }
}
