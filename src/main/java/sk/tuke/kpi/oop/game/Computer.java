package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private Animation computerOn;
    private Animation computerOff;

    boolean electroFlow;

    public Computer() {
        this.electroFlow = false;
        computerOn = new Animation("sprites/computer.png", 80, 48, 0.4f, Animation.PlayMode.LOOP_PINGPONG);
        computerOff = new Animation("sprites/computer.png", 80, 48, 0.0f, Animation.PlayMode.LOOP_PINGPONG);
        updateAnimation();
    }

    @Override
    public void setElectricityFlow(boolean setElectricityFlow) {
        this.electroFlow = setElectricityFlow;
    }

    public void updateAnimation() {
        if (electroFlow) {
            setAnimation(computerOn);
        }
        else
            setAnimation(computerOff);
    }

//    private int add(int ok, float bad) {
//        int math = (int) (ok + bad);
//        return math;
//    }
//
//    private float sub(int ok, float bad) {
//        int math = (int) (bad - ok);
//        return math;
//    }
}
