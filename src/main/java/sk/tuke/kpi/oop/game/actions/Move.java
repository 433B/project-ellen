package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Objects;

public class Move<K extends Movable> implements Action<K> {
    private K move;
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

    private Move(Direction direction) {
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
                move.setPosition(move.getPosX() + moveDirection.getDx() * move.getSpeed(),
                    move.getPosY() + moveDirection.getDy() * move.getSpeed());
                if ((getActor().getScene()).getMap().intersectsWithWall(move)) {
                    move.setPosition(move.getPosX() - moveDirection.getDx() * move.getSpeed(),
                        move.getPosY() - moveDirection.getDy() * move.getSpeed());
                    move.collidedWithWall();
                }
            } else {
                stop();
            }

            if (timeExpand == 0) {
                move.startedMoving(moveDirection);
                timeExpand++;
            }
        }
    }

    public void stop() {
        if (move != null) {
            isRuning = true;
            move.stoppedMoving();
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
        return move;
    }

    @Override
    public void setActor(@Nullable K actor) {
        this.move = actor;
    }

    @Override
    public boolean isDone() {
        return isRuning;
    }
}
