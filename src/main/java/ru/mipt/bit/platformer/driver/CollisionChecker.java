package ru.mipt.bit.platformer.driver;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.TreeObstacle;

import java.util.ArrayList;

public class CollisionChecker {

    private final int width = 10;
    private final int height = 8;

    private final ArrayList<Tank> tanks;
    private final ArrayList<TreeObstacle> treeObstacles;
    private final ArrayList<Bullet> bullets;

    public CollisionChecker() {
        this.tanks = new ArrayList<>();
        this.treeObstacles = new ArrayList<>();
        this.bullets = new ArrayList<>();
    }

    public void addMovable(Tank tank) {
        tanks.add(tank);
    }

    public void addImmovable(TreeObstacle treeObstacle) {
        treeObstacles.add(treeObstacle);
    }

    public boolean checkCollisionsWithBullet(GridPoint2 newPosition, Bullet bullet) {
        if (!checkBounds(newPosition)) {
            //TODO уничтожение пули
            return false;
        }
        return checkAllBullets(newPosition, bullet) && checkAllTanksWithBullet(newPosition, bullet) && checkAllTreesWithBullet(newPosition, bullet);
    }

    public boolean checkAllBullets(GridPoint2 newPosition, Bullet bulletToMove) {
        for (var bullet : bullets) {
            if (bullet.equals(bulletToMove)) {
                continue;
            }
            if (!bulletToMove.isMovementPossible(bullet.getCoordinates(), newPosition) || !bulletToMove.isMovementPossible(bullet.getDestinationCoordinates(), newPosition)) {
                //TODO уничтожение обеих пуль
                return false;
            }
        }
        return true;
    }

    public boolean checkAllTanksWithBullet(GridPoint2 newPosition, Bullet bullet) {
        for (var tank : tanks) {
            if (!bullet.isMovementPossible(tank.getCoordinates(), newPosition) || !bullet.isMovementPossible(tank.getDestinationCoordinates(), newPosition)) {
                //TODO уменьшение жизни танка и уничтожение пули
                return false;
            }
        }
        return true;
    }

    public boolean checkAllTreesWithBullet(GridPoint2 newPosition, Bullet bullet) {
        for (var tree : treeObstacles) {
            if (!bullet.isMovementPossible(tree.getCoordinates(), newPosition)) {
                //TODO уничтожение пули
                return false;
            }
        }
        return true;
    }

    public boolean checkCollisionsWithTank(GridPoint2 newPosition, Tank tankToMove) {
        return checkAllTanksWithTank(newPosition, tankToMove) && checkAllTreesWithTank(newPosition, tankToMove) && checkBounds(newPosition);
    }

    public boolean checkAllTanksWithTank(GridPoint2 newPosition, Tank tankToMove) {
        for (var tank : tanks) {
            if (tank.equals(tankToMove)) {
                continue;
            }
            if (!tankToMove.isMovementPossible(tank.getCoordinates(), newPosition) || !tankToMove.isMovementPossible(tank.getDestinationCoordinates(), newPosition))
                return false;
        }
        return true;
    }

    public boolean checkAllTreesWithTank(GridPoint2 newPosition, Tank tankToMove) {
        for (var tree : treeObstacles) {
            if (!tankToMove.isMovementPossible(tree.getCoordinates(), newPosition))
                return false;
        }
        return true;
    }

    boolean checkBounds(GridPoint2 newPosition) {
        int x = newPosition.x, y = newPosition.y;
        return (x >= 0 && x < width && y >= 0 && y < height);
    }
}
