package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.Reactor;

public class TrainingGameplay implements SceneListener {

    public TrainingGameplay() {}

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Reactor reactor = new Reactor();
        scene.addActor(reactor, 64, 64);
        reactor.turnOn();

        Cooler cooler = new Cooler(reactor);
        scene.addActor(cooler, 40, 40);
        new ActionSequence<>(
            new Wait<>(4),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);
    }
}
