package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool extends AbstractActor implements Usable {
    private int remainingUses;

    public BreakableTool(int numerosity) {
        this.remainingUses = numerosity;
    }

    public void use() {
        this.remainingUses--;

        if (this.remainingUses == 0) {
            this.getScene().removeActor(this);
        }
    }

//    @Override
//    public void useWith(Actor actor) {
//        this.remainingUses--;
//
//        if (this.remainingUses == 0) {
//            this.getScene().removeActor(this);
//        }
//    }

}
