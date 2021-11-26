package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

public interface Movable extends Actor {

    default int getSpeed() {
        return 0;
    }

    default void startedMoving(Direction direction) {
    }

    default void stoppedMoving() {

    }

    default void collidedWithWall() {}
}
