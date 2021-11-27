package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving implements Behaviour<Movable> {
    private int positionX;
    private int positionY;
    private Direction direction;

    public RandomlyMoving() {
    }

    @Override
    public void setUp(Movable movable) {
        if (movable != null) {
            new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(this::randomMove),
                    new Wait<>(2)
                )).scheduleFor(movable);
        }
    }

    public void randomMove(Movable actor) {
        this.positionX = (int) (Math.random() * (3)) - 1;
        this.positionY = (int) (Math.random() * (3)) - 1;
        this.direction = null;

        for (Direction directions : Direction.values()) {
            if (positionY == directions.getDy() && positionX == directions.getDx()) {
                this.direction = directions;
            }
        }
        assert direction != null;
        actor.getAnimation().setRotation(direction.getAngle());
        new Move<>(direction, 2).scheduleFor(actor);
    }
}
