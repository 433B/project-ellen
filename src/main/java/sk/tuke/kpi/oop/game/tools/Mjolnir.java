package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Mjolnir extends BreakableTool<Reactor>{
    private Animation mjolnerAnimation;

    public Mjolnir() {
        super(4);

        mjolnerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(mjolnerAnimation);
    }

    @Override
    public void useWith(Reactor actor) {
        if (actor == null) {
            return;
        }
        actor.repair();
        super.useWith(actor);
    }
}
