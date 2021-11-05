package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool<DefectiveLight> {
    private Animation wrenchAnimation;

    public Wrench() {
        super(2);

        wrenchAnimation = new Animation("sprites/wrench.png", 16, 16);
        setAnimation(wrenchAnimation);
    }

    @Override
    public void useWith(DefectiveLight actor) {
        if (actor == null && getScene() != null) {
            getScene().removeActor(this);
            return;
        }
        if (actor != null) {
            actor.repair();
        }
        super.useWith(actor);
    }
}
