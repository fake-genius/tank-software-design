package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.driver.CollisionChecker;

import java.util.Objects;

public class Bullet implements GameObject{
    public final float damage = 10;

    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;

    private final float direction;

    private final CollisionChecker collisionChecker;

    public Bullet(GridPoint2 coords, float direction, CollisionChecker collisionChecker) {
        this.coordinates = new GridPoint2(coords);
        this.destinationCoordinates = new GridPoint2(coords);
        this.direction = direction;

        this.collisionChecker = collisionChecker;
    }

    public GridPoint2 getCoordinates() {
        return this.coordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return this.destinationCoordinates;
    }

    public void live(float deltaTime) {

    }

    public boolean checkCollisions(GridPoint2 newPosition) {
        return collisionChecker.checkCollisionsWithBullet(newPosition, this);
    }

    public boolean isMovementPossible(GridPoint2 obstacleCoordinates, GridPoint2 newPosition) {
        return !obstacleCoordinates.equals(newPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bullet bullet = (Bullet) o;
        return Float.compare(bullet.direction, direction) == 0 && coordinates.equals(bullet.coordinates) && destinationCoordinates.equals(bullet.destinationCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(damage, coordinates, destinationCoordinates, direction);
    }
}
