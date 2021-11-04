package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private Animation computerOn;
    private Animation computerOff;

    private boolean electroFlow;

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

    public int add(int ok, int bad) {
        int math = ok + bad;
        return math;
    }

    public float add(float ok, float bad) {
        float math = ok + bad;
        return math;
    }

    public float sub(float ok, float bad) {
        float math = bad - ok;
        return math;
    }

    public int sub(int ok, int bad) {
        int math = bad - ok;
        return math;
    }
}
