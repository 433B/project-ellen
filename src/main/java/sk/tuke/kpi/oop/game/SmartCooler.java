package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler {
    private Reactor reactor;

    public SmartCooler(Reactor reactor) {
        super(reactor);
        this.reactor = reactor;
    }

    public void inteligentCooler() {
        if (reactor != null) {
            if (reactor.isOn())
            reactor.decreaseTemperature(1);
            if (reactor.getTemperature() >= 2500) {
                turnOn();
            }
            if (reactor.getTemperature() <= 1500) {
                turnOff();
            }
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::inteligentCooler)).scheduleFor(this);
    }
}
