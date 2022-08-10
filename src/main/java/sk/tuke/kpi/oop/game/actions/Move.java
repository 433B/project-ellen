package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;


public class Move
    <K extends Movable> implements Action<K> {
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
        if (getActor() == null) {
            return;
        }

        timeDuration -= deltaTime;

        if (!isDone()) {
            if (timeExpand == 0 ) {
                movable.startedMoving(moveDirection);
                timeExpand = timeExpand + 1;
            }

            if (timeDuration > 0) {
                movable
                    .setPosition(movable.getPosX()
                        + moveDirection.getDx()
                        * movable.getSpeed(),

                        movable.getPosY()
                            + moveDirection.getDy()
                            * movable.getSpeed());

                if ((getActor()
                    .getScene())
                    .getMap()
                    .intersectsWithWall(movable)) {

                    movable
                        .setPosition(movable.getPosX()
                            - moveDirection.getDx()
                            * movable.getSpeed(),

                            movable.getPosY()
                                - moveDirection.getDy()
                                * movable.getSpeed());

                    movable.collidedWithWall();
                }
            } else
                stop();
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
        movable.stoppedMoving();
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
