package ru.mipt.bit.platformer.AIControl;

import org.awesome.ai.AI;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import ru.mipt.bit.platformer.AIControl.creators.GameStateCreator;
import ru.mipt.bit.platformer.control.Command;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;
import java.util.List;

public class TankAIController {
    private final AI ai;
    private GameState gameState;
    private final ArrayList<Command> tanksCommands;

    private final Tank playerTank;
    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> treeObstacles;

    private final int width;
    private final int height;

    public TankAIController(AI ai, Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks, int levelWidth, int levelHeight) {
        this.ai = ai;
        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;
        this.width = levelWidth;
        this.height = levelHeight;
        GameStateCreator gameStateCreator = new GameStateCreator();
        this.gameState = gameStateCreator.createGameState(this.playerTank, this.treeObstacles, this.tanks, width, height);
        this.tanksCommands = new ArrayList<>();
    }

    public ArrayList<Command> getCommands() {
        recommendCommands();
        return tanksCommands;
    }

    public void executeCommands() {
        recommendCommands();
        for (Command command : tanksCommands) {
            command.execute();
        }
    }

    public void createGameState() {
        GameStateCreator gameStateCreator = new GameStateCreator();
        this.gameState = gameStateCreator.createGameState(playerTank, treeObstacles, tanks, width, height);
    }

    public void recommendCommands() {
        createGameState();
        List<Recommendation> recommendations = ai.recommend(gameState);
        CommandFromRecommendation commandFromRecommendation = new CommandFromRecommendation();
        for (Recommendation rec : recommendations) {
            tanksCommands.add(commandFromRecommendation.getCommand(rec));
        }
    }

}
