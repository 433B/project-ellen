package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool <A extends Actor> extends AbstractActor implements Usable<A> {
    private int remainingUses;

    public BreakableTool(int numerosity) {
        this.remainingUses = numerosity;
    }

    @Override
    public void useWith(A actor) {
        this.remainingUses--;

        if (this.remainingUses == 0 && getScene() != null) {
            this.getScene().removeActor(this);
        }
    }

    public int getRemainingUses(int i) {
        return remainingUses = i;
    }
}
