package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Controller extends AbstractActor {
    private Reactor reactor;

    private Animation switchAnimation;

    public Controller() {
        this.reactor = reactor;

        switchAnimation = new Animation("sprites/switch.png", 16, 16, 0.4f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(switchAnimation);
    }

    public void toggle(Reactor reactor) {
        if (reactor != null) {
            if(reactor.isRunning() == false) {
                reactor.turnOn();
            }
            else{
                reactor.turnOff();
            }
        }
    }
}
