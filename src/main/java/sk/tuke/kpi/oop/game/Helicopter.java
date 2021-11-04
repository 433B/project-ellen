package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    Animation helicopterAnimation;

    public Helicopter() {
        helicopterAnimation = new Animation("sprites/heli.png", 64, 192);
        setAnimation(helicopterAnimation);
    }

    public void searchAndDestroy() {

    }
}
