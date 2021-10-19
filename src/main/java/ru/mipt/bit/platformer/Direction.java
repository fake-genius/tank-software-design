package ru.mipt.bit.platformer;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    Up,
    Down,
    Left,
    Right;

    public Direction getRandomDirection() {
        int num = ThreadLocalRandom.current().nextInt(0, 10000) % 4;
        switch (num) {
            case 0:
                return Up;
            case 1:
                return Down;
            case 2:
                return Left;
            case 3:
                return Right;
        }
        return Up;
    }
}
