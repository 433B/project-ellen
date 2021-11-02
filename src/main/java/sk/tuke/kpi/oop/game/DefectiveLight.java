package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable {
    public int number;

    public DefectiveLight() {
        super();
    }

    public void randomNumber() {
        if (isOn() ) {
            number = (int) (Math.random() * 20);
            if (number == 5 || number == 10) {
                setAnimation(lightOff);
            } else setAnimation(lightOn);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::randomNumber)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        return false;
    }
}
