package ru.mipt.bit.platformer.gameobjects;

/**
 * Entity
 */
public interface GameObject {

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();
}
