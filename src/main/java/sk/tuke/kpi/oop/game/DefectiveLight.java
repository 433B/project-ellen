package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class DefectiveLight extends Light implements Repairable {
    private Disposable disposable;
    private boolean isOn;

    public DefectiveLight() {
        super();
        this.isOn = false;
    }

    private void bloom() {
        int i = (int) (Math.random() * Math.nextDown(20));

        if (i == 10) {
            toggle();
        }
    }

    @Override
    public boolean repair() {
        if (!isOn) {
            isOn = true;
            disposable.dispose();
            this.turnOn();
            new ActionSequence<>(
                new Wait<>(10),
                new Invoke<>(this::refresh)
            ).scheduleFor(this);
            return true;
        } else {
            return false;
        }
    }

    private void refresh() {
        disposable = new Loop<>(new Invoke<>(this::bloom)).scheduleFor(this);
        isOn = false;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        disposable = new Loop<>(new Invoke<>(this::bloom)).scheduleFor(this);
    }
}
