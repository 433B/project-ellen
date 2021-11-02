package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends BreakableTool {
    private Animation hammerAnimation;

    public Hammer(int numerosity) {
        super(numerosity);

        hammerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(hammerAnimation);
    }

    Hammer() {
        super(2);
    }
}
