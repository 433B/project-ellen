package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Objects;

public class Move<K extends Movable> implements Action<K> {
    private K movable;
    private final Direction moveDirection;
    private boolean isRunning;
    private float timeDuration;
    private int timeExpand;

    public Move(Direction direction, float duration) {
        this.isRunning = false;
        this.timeExpand = 0;
        this.moveDirection = direction;
        this.timeDuration = duration;
    }

    @Override
    public void execute(float deltaTime) {
        timeDuration -= deltaTime;

        if (getActor() != null && !isDone()) {
            getActor().getScene().getMap();

            getPositionForMoving();

            if (timeExpand == 0) {
                movable.startedMoving(moveDirection);
                timeExpand++;
            }
        }
    }

    private void getPositionForMoving() {
        int x = movable.getPosX() + moveDirection.getDx() * movable.getSpeed();
        int y = movable.getPosY() + moveDirection.getDy() * movable.getSpeed();

        if (timeDuration > 0) {
            movable.setPosition(x, y);
            movingCoordinate();
        } else stop();
    }

    private void movingCoordinate() {
        if ((getActor().getScene()).getMap().intersectsWithWall(movable)) {
            int x = movable.getPosX() - moveDirection.getDx() * movable.getSpeed();
            int y = movable.getPosY() - moveDirection.getDy() * movable.getSpeed();
            movable.setPosition(x, y);
            movable.collidedWithWall();
        }
    }

    public void stop() {
        if (movable != null) {
            isRunning = true;
            movable.stoppedMoving();
        }
    }

    @Override
    public void reset() {
        this.timeExpand = 0;
        this.timeDuration = 0;
        this.isRunning = false;
    }

    @Nullable
    @Override
    public K getActor() {
        return movable;
    }

    @Override
    public void setActor(@Nullable K actor) {
        this.movable = actor;
    }

    @Override
    public boolean isDone() {
        return isRunning;
    }
}
