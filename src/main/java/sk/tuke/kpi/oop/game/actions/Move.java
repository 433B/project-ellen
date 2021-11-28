package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Objects;

public class Move<K extends Movable> implements Action<K> {
    private K movable;
    private Direction moveDirection;
    private boolean isRuning;
    private float timeDuration;
    private int timeExpand;

    public Move(Direction direction, float duration) {
        this.isRuning = false;
        this.timeExpand = 0;
        this.moveDirection = direction;
        this.timeDuration = duration;
    }

    public Move(Direction direction) {
        this.isRuning = false;
        this.timeExpand = 0;
        this.moveDirection = direction;
    }

    @Override
    public void execute(float deltaTime) {
        timeDuration -= deltaTime;

        if (getActor() != null && !isDone()) {
            Objects.requireNonNull(getActor().getScene()).getMap();
            if (timeDuration > 0) {
                movable.setPosition(movable.getPosX() + moveDirection.getDx() * movable.getSpeed(),
                    movable.getPosY() + moveDirection.getDy() * movable.getSpeed());
                if ((getActor().getScene()).getMap().intersectsWithWall(movable)) {
                    movable.setPosition(movable.getPosX() - moveDirection.getDx() * movable.getSpeed(),
                        movable.getPosY() - moveDirection.getDy() * movable.getSpeed());
                    movable.collidedWithWall();
                }
            } else {
                stop();
            }

            if (timeExpand == 0) {
                movable.startedMoving(moveDirection);
                timeExpand++;
            }
        }
    }

    public void stop() {
        if (movable != null) {
            isRuning = true;
            movable.stoppedMoving();
        }
    }

    @Override
    public void reset() {
        this.timeExpand = 0;
        this.timeDuration = 0;
        this.isRuning = false;
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
        return isRuning;
    }
}
