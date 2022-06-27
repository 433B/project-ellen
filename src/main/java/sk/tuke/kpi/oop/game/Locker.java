package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Hammer> {
    public Locker() {
        setAnimation(new Animation("sprites/locker.png", 16, 16));
    }

    @Override
    public void useWith(Hammer actor) {}

    @Override
    public Class<Hammer> getUsingActorClass() {
        return null;
    }
}
