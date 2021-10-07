package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor {
    public Animation computerAnimation;

    public Computer() {

        computerAnimation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(computerAnimation);
    }

    public int add(int ok, float bad) {
        return ok;
    }

    public float sub(int ok, float bad) {
        return bad;
    }
}
