package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> {
    private Animation extinguisherAnimation;

    public FireExtinguisher() {
        super(1);
        extinguisherAnimation = new Animation("sprites/extinguisher.png", 16, 16);
        setAnimation(extinguisherAnimation);
    }

    @Override
    public void useWith(Reactor actor) {
        if (actor == null) {
            return;
        }

        if (actor.getDamage() == 100) {
            actor.extinguih();
            super.useWith(actor);
        }
    }
}
