package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer
    extends AbstractActor implements EnergyConsumer {
    private final Animation computerOn;
    private final Animation computerOff;

    private boolean electricFlow;

    public Computer() {
        computerOn = new Animation(
            "sprites/computer.png",
            80, 48,
            0.4f, Animation.PlayMode.LOOP_PINGPONG);

        computerOff = new Animation(
            "sprites/computer.png",
            80, 48,
            0.0f, Animation.PlayMode.LOOP_PINGPONG);

        updateAnimation();
    }

    @Override
    public void setPowered(boolean a) {
        this.electricFlow = a;
        updateAnimation();
    }

    public void updateAnimation() {
        if (this.electricFlow) {
            setAnimation(computerOn);
        } else
            setAnimation(computerOff);
    }

    public int add(int ok, int bad) {
        if (electricFlow) {
            int math;
            math = ok + bad;

            return math;
        } else return 0;
    }

    public float add(float ok, float bad) {
        if (electricFlow) {
            float math;
            math = ok + bad;

            return math;
        } else return 0;
    }

    public float sub(float ok, float bad) {
        if (electricFlow) {
            float math;
            math = ok - bad;

            return math;
        } else return 0;
    }

    public int sub(int ok, int bad) {
        if (electricFlow) {
            int math;
            math = ok - bad;

            return math;
        } else return 0;
    }
}
