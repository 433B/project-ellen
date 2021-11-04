package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {
    private Animation coolerAnimation;
    private Animation coolerOffAnimetion;

    private Reactor reactor;
    private boolean check;

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        this.check = false;

        coolerAnimation = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        coolerOffAnimetion = new Animation("sprites/fan.png", 32, 32, 0.0f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(coolerOffAnimetion);
    }

    @Override
    public boolean isOn() {
        return check;
    }

    @Override
    public void turnOn() {
        this.check = true;
        updateAimation();
    }

    @Override
    public void turnOff() {
        check = false;
        updateAimation();
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

    private void updateAimation() {
        if (isOn()) {
            setAnimation(coolerAnimation);
        } else {
            setAnimation(coolerOffAnimetion);
        }
    }
}
