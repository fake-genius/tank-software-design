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
    private final GameState gameState;
    private final ArrayList<Command> tanksCommands;

    public TankAIController(AI ai, Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks, int levelWidth, int levelHeight) {
        this.ai = ai;
        GameStateCreator gameStateCreator = new GameStateCreator();
        this.gameState = gameStateCreator.createGameState(playerTank, treeObstacles, tanks, levelWidth, levelHeight);
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

    public void recommendCommands() {
        List<Recommendation> recommendations = ai.recommend(gameState);
        CommandFromRecommendation commandFromRecommendation = new CommandFromRecommendation();
        for (Recommendation rec : recommendations) {
            tanksCommands.add(commandFromRecommendation.getCommand(rec));
        }
    }

}
