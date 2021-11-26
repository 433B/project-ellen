package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.Objects;

public class Alien extends AbstractActor implements Movable, Enemy, Alive {
    private Health health;
    private Behaviour<? super Alien> alienBehaviour;

    public Alien() {
        health = new Health(100, 100);
        health.onExhaustion(() -> Objects.requireNonNull(getScene()).removeActor(this));
        setAnimation( new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_REVERSED));
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    public void ellenDied() {
        Objects.requireNonNull(getScene()).removeActor(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (Objects.nonNull(alienBehaviour)) {
            alienBehaviour.setUp(this);
        }

        this.health.onExhaustion(this::ellenDied);
        scene.getActors().forEach(
            actor -> {
                if (actor instanceof Alive && !(actor instanceof Enemy)) {
                    Alive aliveActor = (Alive) actor;
                    new Loop<>(
                        new When<>(
                            () -> actor.intersects(this),
                            new Invoke<>(() -> aliveActor.getHealth().drain(1))
                        )
                    ).scheduleFor(this);
                }
            }
        );
    }

    @Override
    public void startedMoving (Direction direction) {
        this.getAnimation().play();
        this.getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public void stoppedMoving () {
        this.getAnimation().pause();
    }

}
