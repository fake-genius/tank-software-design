package ru.mipt.bit.platformer.AIControl.creators;

import org.awesome.ai.state.movable.Bot;
import ru.mipt.bit.platformer.gameobjects.Tank;

public class BotCreator {
    public Bot createBot(Tank tank, OrientationCreator orientationCreator) {
        return new Bot.BotBuilder()
                .source(tank)
                .x(tank.getCoordinates().x)
                .y(tank.getCoordinates().y)
                .destX(tank.getDestinationCoordinates().x)
                .destY(tank.getDestinationCoordinates().y)
                .orientation(orientationCreator.createOrientation(tank.getCoordinates(), tank.getDestinationCoordinates()))
                .build();
    }
}
