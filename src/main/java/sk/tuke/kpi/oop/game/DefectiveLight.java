package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class DefectiveLight extends Light implements Repairable {
    private double number;
    private boolean stop;
    private boolean isOn;
//    private boolean isPowered;

    private Animation lightOn;
    private Animation lightOff;
    private Disposable disposable;

    public DefectiveLight() {
        super();
        this.stop = false;

        lightOff = new Animation("sprites/light_off.png", 16, 16);
        lightOn = new Animation("sprites/light_on.png", 16, 16);
        setAnimation(lightOff);
    }

    @Override
    public void setPowered(boolean a) {
        this.isOn = a;
    }

    public void randomNumber() {
        if (isOn) {
            number = (int) (Math.random() * Math.nextDown(20));
            if (number == 1) {
                setAnimation(lightOn);
            } else if (number == 2) {
                setAnimation(lightOff);
            }
        }
    }

    @Override
    public boolean repair() {
        if (isPowered() && !stop) {
            disposable.dispose();
            setAnimation(lightOn);
            stop = true;
            new ActionSequence<>(
                new Wait<>(10),
                new Invoke<>(this::refresh)
            ).scheduleFor(this);
            return true;
        }
        return false;
    }

    private void refresh() {
        disposable = new Loop<>(new Invoke<>(this::randomNumber)).scheduleFor(this);
        stop = false;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        disposable = new Loop<>(new Invoke<>(this::randomNumber)).scheduleFor(this);
    }

    @Override
    public void turnOn() {
        isOn = true;
        updateAnimation();
    }

    @Override
    public void turnOff() {
        isOn = false;
        updateAnimation();
    }

    @Override
    public boolean isOn() {
        return this.isOn;
    }

    public boolean isPowered() {
        return isOn;
    }
}
