package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.math.GridPoint2;

public enum GameObjectType {
    PLAYER {
        public GameObject getObject(GridPoint2 coords) {
            return new Tank(coords);
        }
    },
    TREE {
        public GameObject getObject(GridPoint2 coords) {
            return new TreeObstacle(coords);
        }
    };

    public abstract GameObject getObject(GridPoint2 coords);
}
