package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> {
    private Animation hammerAnimation;

    public Hammer() {
        super(1);
        hammerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(hammerAnimation);
    }

    @Override
    public void useWith(Reactor actor) {
        if (actor == null) {
            return;
        }
        if (actor.getTemperature() > 0) {
            actor.repair();
            super.useWith(actor);
        }
    }
}
