package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private Animation hammerAnimation;

    private int count;

    public Hammer() {
        this.count = 1;

        hammerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(hammerAnimation);
    }

    public void use() {
        this.count--;
        if (this.count == 0) {
            this.getScene().removeActor(this);
        }
    }

    public int getCount() {
        return this.count;
    }
}
