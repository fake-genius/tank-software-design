package ru.mipt.bit.platformer.AIControl.creators;

import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;

/**
 * Adapter
 */
public class GameStateCreator {
    public GameState createGameState(Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks, int levelWidth, int levelHeight) {
        GameState.GameStateBuilder gameStateBuilder = new GameState.GameStateBuilder();
        return gameStateBuilder
                .obstacles(getObstacles(treeObstacles))
                .bots(getBots(tanks))
                .player(getPlayer(playerTank))
                .levelWidth(levelWidth)
                .levelHeight(levelHeight)
                .build();
    }

    Player getPlayer(Tank playerTank) {
        PlayerCreator playerCreator = new PlayerCreator();
        return playerCreator.createPlayer(playerTank, new OrientationCreator());
    }

    ArrayList<Bot> getBots(ArrayList<Tank> tanks) {
        BotCreator botCreator = new BotCreator();
        OrientationCreator orientationCreator = new OrientationCreator();
        ArrayList<Bot> bots = new ArrayList<>();
        for (Tank tank : tanks) {
            bots.add(botCreator.createBot(tank, orientationCreator));
        }
        return bots;
    }

    ArrayList<Obstacle> getObstacles(ArrayList<TreeObstacle> treeObstacles) {
        ObstacleCreator obstacleCreator = new ObstacleCreator();
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        for (TreeObstacle tree : treeObstacles) {
            obstacles.add(obstacleCreator.createObstacle(tree));
        }
        return obstacles;
    }
}
