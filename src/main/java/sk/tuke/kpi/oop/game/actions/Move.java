package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Objects;

public class Move <A extends Movable> implements Action<Movable> {
    private Movable movable;
    private Direction direction;
    private float durationSeconds;
    private int firstTime;
    private boolean isRuning;


    public Move(Direction direction, float duration) {
        this.direction = direction;
        this.durationSeconds = duration;
        isRuning = false;
        firstTime = 0;
    }

    public Move(Direction direction) {
        this.direction = direction;
        isRuning = false;
        firstTime = 0;
    }


    @Nullable
    @Override
    public Movable getActor() {
        return movable;
    }

    @Override
    public void setActor(@Nullable Movable movable) {
        this.movable = movable;
    }

    @Override
    public boolean isDone() {
        return isRuning;
    }

    public void stop() {
        isRuning = true;
        movable.stoppedMoving();
    }

    @Override
    public void execute(float deltaTime) {
        durationSeconds = durationSeconds - deltaTime;

        if (!isDone() && getActor() != null) {
            if (durationSeconds > 0) {
                movable.setPosition(movable.getPosX() + direction.getDx() * movable.getSpeed(), movable.getPosY() + direction.getDy() * movable.getSpeed());
                if ((Objects.requireNonNull(getActor().getScene())).getMap().intersectsWithWall(movable)) {
                    movable.setPosition(movable.getPosX() - direction.getDx() * movable.getSpeed(), movable.getPosY() - direction.getDy() * movable.getSpeed());
                }
            } else
                stop();

            if (firstTime == 0 ) {
                movable.startedMoving(direction);
                firstTime = firstTime + 1;
            }
        }
    }

    @Override
    public void reset() {
        if (movable != null) {
            isRuning = false;
            firstTime = 0;
            durationSeconds = 0;
            movable.stoppedMoving();
        }
    }
}
