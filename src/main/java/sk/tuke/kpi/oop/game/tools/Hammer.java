package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool {
    private Animation hammerAnimation;

    private Reactor reactor;

    public Hammer(int numerosity) {
        super(2);
//        reactor.repair();
        hammerAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(hammerAnimation);
    }
}
