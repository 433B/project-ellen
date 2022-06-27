package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {
    private final Animation coolerAnimation;
    private final Animation coolerOffAnimation;

    private final Reactor reactor;
    private boolean check;

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        this.check = false;

        coolerAnimation = new Animation("sprites/fan.png",
            32, 32,
            0.2f, Animation.PlayMode.LOOP_REVERSED);

        coolerOffAnimation = new Animation("sprites/fan.png",
            32, 32,
            0.0f, Animation.PlayMode.LOOP_REVERSED);

        setAnimation(coolerOffAnimation);
    }

    @Override
    public boolean isOn() {
        return check;
    }

    @Override
    public void turnOn() {
        this.check = true;
        updateAnimation();
    }

    @Override
    public void turnOff() {
        check = false;
        updateAnimation();
    }

    private void coolReactor() {
        if (isOn() && reactor != null) {
            reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }

    private void updateAnimation() {
        if (isOn()) {
            setAnimation(coolerAnimation);
        } else {
            setAnimation(coolerOffAnimation);
        }
    }
}
