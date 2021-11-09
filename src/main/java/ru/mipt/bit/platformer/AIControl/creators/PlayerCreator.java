package ru.mipt.bit.platformer.AIControl.creators;

import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class PlayerCreator {
    public Player createPlayer(Tank tank, OrientationCreator orientationCreator) {
        return new Player.PlayerBuilder()
                .source(tank)
                .x(tank.getCoordinates().x)
                .y(tank.getCoordinates().y)
                .destX(tank.getDestinationCoordinates().x)
                .destY(tank.getDestinationCoordinates().y)
                .orientation(orientationCreator.createOrientation(tank.getCoordinates(), tank.getDestinationCoordinates()))
                .build();
    }
}
