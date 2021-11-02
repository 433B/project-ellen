package sk.tuke.kpi.oop.game.tools;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public class BreakableTool extends AbstractActor implements Usable {
    private int remainingUses;
    private boolean luck;

    public BreakableTool(int numerosity) {
        this.remainingUses = numerosity;
    }

    public void returned(Hammer hammer) {
        if (luck) hammer.useWith(hammer);
        if (!luck) hammer.useWith(null);
    }

    public void runed(Wrench wrench) {
        if (luck) wrench.useWith(wrench);
        if (!luck) wrench.useWith(null);
    }

    public void turned(FireExtinguisher fireExtinguisher) {
        if (luck) fireExtinguisher.useWith(fireExtinguisher);
        if (!luck) fireExtinguisher.useWith(null);
    }

    @Override
    public void useWith(Actor actor) {
        this.remainingUses--;

        if (this.remainingUses == 0) {
            this.getScene().removeActor(this);
        }
    }
}
