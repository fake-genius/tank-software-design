package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.driver.CollisionChecker;
import ru.mipt.bit.platformer.driver.Level;
import ru.mipt.bit.platformer.gameobjects.GameObject;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.observer.Event;
import ru.mipt.bit.platformer.observer.Publisher;
import ru.mipt.bit.platformer.observer.Subscriber;

import java.util.ArrayList;
import java.util.Date;

import static com.badlogic.gdx.Input.Keys.*;

public class ControlForPlayer implements Publisher {

    private final ArrayList<Subscriber> subscribers = new ArrayList<>();
    private long lastTimeChanged =  new Date().getTime();

    public Command processKey(Tank tank, Level level) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new MoveUpCommand(tank);
        }
        else if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new MoveLeftCommand(tank);
        }
        else if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new MoveDownCommand(tank);
        }
        else if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new MoveRightCommand(tank);
        }
        else if (Gdx.input.isKeyPressed(SPACE)) {
            return new ShootCommand(tank, level);
        } else if (Gdx.input.isKeyPressed(L)) {
            long time = new Date().getTime();
            if (time - lastTimeChanged > 300) {
                notifySubs(Event.ChangeHealth, null);
                lastTimeChanged = time;
            }
        }
        return new NotMoveCommand(tank);
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubs(Event event, GameObject gameObject) {
        for (Subscriber sub : subscribers)
            sub.update(event, gameObject);
    }
}
