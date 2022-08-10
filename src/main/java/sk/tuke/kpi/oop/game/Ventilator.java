package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;


public class Ventilator
    extends AbstractActor implements Repairable {
    private boolean broken;
    public static final Topic<Ventilator> VENTILATOR_REPAIRED =
        Topic.create("ventilator repaired", Ventilator.class);

    public Ventilator() {
        setAnimation(
            new Animation
                ("sprites/ventilator.png",
                    32,
                    32,
                    0.1f,
                    Animation.PlayMode.LOOP_PINGPONG));
    }

    private void brokenVentilator() {
        getAnimation().stop();
        broken = true;
    }

    @Override
    public boolean repair() {
        if (broken) {
            getAnimation().play();
            broken = false;
            getScene()
                .getMessageBus()
                .publish(VENTILATOR_REPAIRED,
                    this);
            return true;
        }

        return false;
    }
}
