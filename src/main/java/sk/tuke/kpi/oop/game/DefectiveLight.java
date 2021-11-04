package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class DefectiveLight extends Light implements Repairable {
    private int number;

    private Animation lightOn;
    private Animation lightOff;

    public DefectiveLight() {
        super();

        lightOff = new Animation("sprites/light_off.png", 16, 16);
        lightOn = new Animation("sprites/light_on.png", 16, 16);
    }

    public void randomNumber() {
        if (isOn()) {
            number = (int) (Math.random() * 20);
            if (number == 5 || number == 10 && isOn()) {
                setAnimation(lightOn);
            } else setAnimation(lightOff);
        }
    }

    public void setLightOn() {
        if(isOn())
        setAnimation(lightOn);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::randomNumber)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        if (!this.isOn()) {
            return false;
        } else {
            new ActionSequence<DefectiveLight>(
                new Invoke<>(this::turnOff),
                new Invoke<>(this::setLightOn),
                new Wait<>(10),
                new Invoke<>(this::turnOn)
            ).scheduleFor(this);
            return true;
        }
    }
}
