package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;

public class Alien extends AbstractActor implements Movable, Enemy, Alive {

    public Alien() {
        setAnimation( new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_REVERSED));
    }

    @Override
    public Health getHealth() {
        return null;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
    }

    @Override
    public int getSpeed() {
        return 0;
    }
}
