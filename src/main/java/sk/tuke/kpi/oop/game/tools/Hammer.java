package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> {
    private Animation hammerAnimation;

    public Hammer(int i) {
        super(1);
        hammerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(hammerAnimation);
    }

    @Override
    public void useWith(Reactor actor) {
        if (actor != null) {
            actor.repair();
            super.useWith(actor);
        }
    }
}
