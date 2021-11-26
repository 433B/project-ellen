package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Energy extends AbstractActor implements Usable<Alive> {

    private Ripley ripley;

    public Energy() {
        setAnimation(new Animation("sprites/energy.png", 16,16));
    }

    @Override
    public void useWith(Alive actor) {

    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }

}
