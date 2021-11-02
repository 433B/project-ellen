package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Mjolnir extends Hammer {
    private Animation mjolnerAnimation;

    public Mjolnir(int numerosity) {
        super(4);

        mjolnerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(mjolnerAnimation);
    }
}
