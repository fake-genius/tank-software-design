package ru.mipt.bit.platformer.AIControl;

import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.control.*;
import ru.mipt.bit.platformer.gameobjects.Tank;

/**
 * Adapter
 */
public class CommandFromRecommendation {
    Command getCommand(Recommendation recommendation) {
        Tank actor = (Tank) recommendation.getActor().getSource();
        Action action = recommendation.getAction();
        switch(action) {
            case MoveEast:
                return new MoveRightCommand(actor);
            case MoveWest:
                return new MoveLeftCommand(actor);
            case MoveNorth:
                return new MoveUpCommand(actor);
        }
        return new MoveDownCommand(actor);
    }
}
