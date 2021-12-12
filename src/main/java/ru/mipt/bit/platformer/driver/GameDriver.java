package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.Gdx;
import org.awesome.ai.AI;
import ru.mipt.bit.platformer.AIControl.TankAIController;
import ru.mipt.bit.platformer.control.Command;
import ru.mipt.bit.platformer.control.ControlForBots;
import ru.mipt.bit.platformer.control.ControlForPlayer;
import ru.mipt.bit.platformer.control.Controller;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.GameObject;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;
import ru.mipt.bit.platformer.graphics.LevelRenderer;
import ru.mipt.bit.platformer.observer.Event;
import ru.mipt.bit.platformer.observer.Subscriber;

import java.util.ArrayList;

/**
 * Adapter
 */
public class GameDriver implements Subscriber {
    private final int width = 10;
    private final int height = 8;

    private final Tank playerTank;
    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> treeObstacles;

    private final Controller controlForPlayer;
    private final Controller controlForBots;
    private final Level level;

    private final ArrayList<Command> commands;

    public GameDriver(Level level, AI ai, LevelRenderer levelRenderer) {
        this.playerTank = level.getPlayerTank();
        this.treeObstacles = level.getTreeObstacles();
        this.tanks = level.getTanks();

        this.controlForPlayer = new ControlForPlayer();
        ((ControlForPlayer)this.controlForPlayer).subscribe(levelRenderer);
        this.controlForBots = new ControlForBots();
        //this.controlForBots = new TankAIController(ai, playerTank, treeObstacles, tanks, width, height);

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
        ArrayList<Tank> playerList = new ArrayList<>();
        playerList.add(playerTank);
        commands.addAll(controlForPlayer.getCommands(playerList, level));
    }

    public void generateCommandsBots() {
        commands.addAll(controlForBots.getCommands(tanks, level));
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
