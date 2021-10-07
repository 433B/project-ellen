package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Mjolnir extends Hammer {
    private int hammer;

    private Animation mjolnerAnimation;

    public Mjolnir() {
        this.hammer = 4;

        mjolnerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(mjolnerAnimation);
    }

    public void use() {
        this.hammer--;

        if (this.hammer == 0) {
            this.getScene().removeActor(this);
        }
    }
}
