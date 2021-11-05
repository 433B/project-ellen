package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> {

    private int using;

    public Hammer() {
        super(1);
        this.using = 1;

        setAnimation(new Animation("sprites/hammer.png", 16, 16));
    }

    public int getUsing(int using) {
        return this.using = using;
    }

    @Override
    public void useWith(Reactor actor) {
        if (actor != null) {
            actor.repair();
            super.useWith(actor);
        }
    }
}
