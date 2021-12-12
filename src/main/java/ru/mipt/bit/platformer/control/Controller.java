package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.driver.Level;
import ru.mipt.bit.platformer.gameobjects.Tank;

import java.util.ArrayList;

public interface Controller {
    ArrayList<Command> getCommands(ArrayList<Tank> tank, Level level);
}
