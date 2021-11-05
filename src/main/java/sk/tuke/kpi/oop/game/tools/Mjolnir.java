package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Mjolnir extends Hammer {
    Animation mjolnerAnimation;
    private int creative;

    public Mjolnir() {
        this.creative = 4;

        mjolnerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(mjolnerAnimation);
    }

    @Override
    public void useWith(Reactor actor) {
        if (actor == null) {
            return;
        }
        super.useWith(actor);
        creative--;
        if (creative == 0 && getScene() != null) {
            getScene().removeActor(this);
        }
    }

    public int getCreativeUses() {
        return creative;
    }
}
