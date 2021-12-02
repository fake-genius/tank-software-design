package ru.mipt.bit.platformer.gameobjects;

public interface GameObject {

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();
}
