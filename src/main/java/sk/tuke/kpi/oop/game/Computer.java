package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private Animation computerOn;
    private Animation computerOff;

    private boolean electroFlow;
    private int math;
    private float geom;

    public Computer() {
        computerOn = new Animation("sprites/computer.png", 80, 48, 0.4f, Animation.PlayMode.LOOP_PINGPONG);
        computerOff = new Animation("sprites/computer.png", 80, 48, 0.0f, Animation.PlayMode.LOOP_PINGPONG);
        updateAnimation();
    }

    @Override
    public void setPowered(boolean a) {
        this.electroFlow = a;
        updateAnimation();
    }

    public void updateAnimation() {
        if (this.electroFlow) {
            setAnimation(computerOn);
        } else
            setAnimation(computerOff);
    }

    public int add(int ok, int bad) {
        if(electroFlow) {
            int math;
            math = ok + bad;
            return math;
        }
        else return 0;
    }

    public float add(float ok, float bad) {
        if (electroFlow) {
            float math;
            math = ok + bad;
            return math;
        }
        else return 0;
    }

    public float sub(float ok, float bad) {
        if (electroFlow) {
            float math;
            math = ok - bad;
            return math;
        }
        else return 0;
    }

    public int sub(int ok, int bad) {
        if (electroFlow) {
            int math;
            math = ok - bad;
            return math;
        }
        else return 0;
    }
}
