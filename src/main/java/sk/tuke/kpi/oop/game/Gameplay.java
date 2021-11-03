package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.tools.Hammer;

public class Gameplay extends Scenario {
    private int waiting;

    public Gameplay() {
        this.waiting = 30;
    }

    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();  // vytvorenie instancie reaktora
        scene.addActor(reactor, 64, 64);  // pridanie reaktora do sceny na poziciu [x=64, y=64]
        reactor.turnOn();

        Cooler cooler = new Cooler(reactor);
        scene.addActor(cooler, 40, 40);
        new ActionSequence<>(
            new Wait<>(waiting),
            new Invoke<>(cooler::turnOn),
            new Wait<>(10),
            new Invoke<>(cooler::turnOff)
        ).scheduleFor(cooler);

        Hammer hammer = new Hammer(2);
        scene.addActor(hammer, 80, 80);
        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() -> hammer.use())
        ).scheduleFor(reactor);

    }
}
