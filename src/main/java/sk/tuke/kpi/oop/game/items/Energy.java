package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Energy extends AbstractActor implements Usable<Alive> {
    public Animation energyAnimation;

    Ripley ripley;

    public Energy() {
        energyAnimation = new Animation("sprites/energy.png", 16,16);
        setAnimation(energyAnimation);
    }

    @Override
    public void useWith(Alive actor) {

    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }

}
