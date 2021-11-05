package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> {

    public FireExtinguisher() {
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png", 16, 16));
    }

    @Override
    public void useWith(Reactor actor) {
        if (actor != null && actor.getDamage() == 100 && actor.getTemperature() == 6000) {
            actor.extinguih();
            super.useWith(actor);
            super.getRemainingUses(1);
        }
    }
}
