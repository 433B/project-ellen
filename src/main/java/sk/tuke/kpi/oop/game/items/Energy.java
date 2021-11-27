package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

import java.util.Objects;

public class Energy extends AbstractActor implements Usable<Alive> {
    public Energy() {
        setAnimation(new Animation("sprites/energy.png", 16,16));
    }

    @Override
    public void useWith(Alive actor) {
        try {
            actor.getHealth().refill(50);
            Objects.requireNonNull(getScene()).removeActor(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
