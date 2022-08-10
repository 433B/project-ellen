package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light
    extends AbstractActor implements Switchable,EnergyConsumer {
    private final Animation lightOn;
    private final Animation lightOff;
    private boolean isOn;
    private boolean isPowered;

    public Light() {
        this.isOn = false;
        lightOff = new Animation(
            "sprites/light_off.png",
            16,
            16);

        lightOn = new Animation(
            "sprites/light_on.png",
            16,
            16);
        setAnimation(lightOff);
        updateAnimation();
    }

    public void toggle() {
        if (!isOn) turnOn();
        else turnOff();
    }

    public void setElectricityFlow(boolean setElectricityFlow) {
        isOn = setElectricityFlow;
        updateAnimation();
    }

    @Override
    public void setPowered(boolean a) {
        isPowered = a;
        updateAnimation();
    }

    public void updateAnimation() {
        if (isOn && isPowered) setAnimation(lightOn);
        else setAnimation(lightOff);
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
}
