package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;

public class CollisionChecker {

    private final int width = 10;
    private final int height = 8;

   // private final Tank playerTank;
    //private final ArrayList<Tank> tanks;
    //private final ArrayList<TreeObstacle> treeObstacles;

    private final ArrayList<Tank> movableGameObjects;
    private final ArrayList<TreeObstacle> immovableGameObjects;

    public CollisionChecker() {
        this.movableGameObjects = new ArrayList<>();
        this.immovableGameObjects = new ArrayList<>();
    }

    public void addMovable(Tank tank) {
        movableGameObjects.add(tank);
    }

    public void addImmovable(TreeObstacle treeObstacle) {
        immovableGameObjects.add(treeObstacle);
    }

    public boolean checkCollisions(GridPoint2 newPosition, Tank tankToMove) {
        return checkAllMovables(newPosition, tankToMove) && checkAllImmovables(newPosition, tankToMove) && checkBounds(newPosition);
    }

    public boolean checkAllMovables(GridPoint2 newPosition, Tank tankToMove) {
        for (var tank : movableGameObjects) {
            if (tank.equals(tankToMove)) {
                continue;
            }
            if (!tankToMove.isMovementPossible(tank.getCoordinates(), newPosition) || !tankToMove.isMovementPossible(tank.getDestinationCoordinates(), newPosition))
                return false;
        }
        return true;
    }

    public boolean checkAllImmovables(GridPoint2 newPosition, Tank tankToMove) {
        for (var tree : immovableGameObjects) {
            if (!tankToMove.isMovementPossible(tree.getCoordinates(), newPosition))
                return false;
        }
        return true;
    }
/*
    public CollisionChecker(Tank playerTank, ArrayList<TreeObstacle> treeObstacles, ArrayList<Tank> tanks) {
        this.playerTank = playerTank;
        this.treeObstacles = treeObstacles;
        this.tanks = tanks;

        this.movableGameObjects = new ArrayList<>();
        this.movableGameObjects.add(playerTank);
        this.movableGameObjects.addAll(tanks);
        this.immovableGameObjects = new ArrayList<>();
        this.immovableGameObjects.addAll(treeObstacles);
    }

    public boolean checkAllObstacles(GridPoint2 newPosition, Tank tankToMove) {
        boolean okPlayer = checkPlayer(newPosition, tankToMove);
        boolean okTrees = checkAllTreeObstacles(newPosition, tankToMove);
        boolean okTanks = checkAllTanks(newPosition, tankToMove);
        boolean okBounds = checkBounds(newPosition);
        return okPlayer && okTrees && okTanks && okBounds;
    }

    boolean checkPlayer(GridPoint2 newPosition, Tank tankToMove) {
        if (tankToMove.equals(playerTank)) {
            return true;
        }
        return tankToMove.isMovementPossible(playerTank.getCoordinates(), newPosition) && tankToMove.isMovementPossible(playerTank.getDestinationCoordinates(), newPosition);
    }

    boolean checkAllTreeObstacles(GridPoint2 newPosition, Tank tankToMove) {
        for (var tree : treeObstacles) {
            if (!tankToMove.isMovementPossible(tree.getCoordinates(), newPosition))
                return false;
        }
        return true;
    }

    boolean checkAllTanks(GridPoint2 newPosition, Tank tankToMove) {
        for (Tank tank : tanks) {
            if (tank.equals(tankToMove)) {
                continue;
            }
            if (!tankToMove.isMovementPossible(tank.getCoordinates(), newPosition) || !tankToMove.isMovementPossible(tank.getDestinationCoordinates(), newPosition))
                return false;
        }
        return true;
    }
*/
    boolean checkBounds(GridPoint2 newPosition) {
        int x = newPosition.x, y = newPosition.y;
        return (x >= 0 && x < width && y >= 0 && y < height);
    }
}
