package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable,EnergyConsumer {

    private Animation lightOn;
    private Animation lightOff;

    private boolean isOn;

    public Light() {
        lightOff = new Animation("sprites/light_off.png", 16, 16);
        lightOn = new Animation("sprites/light_on.png", 16, 16);
        setAnimation(lightOff);
    }

    public void toggle() {
        if (isOn) {
            isOn = false;
            setAnimation(lightOff);
        } else {
            isOn = true;
            setAnimation(lightOn);
        }
    }

    public void setElectricityFlow(boolean setElectricityFlow) {
        this.isOn = setElectricityFlow;
        updateAnimation();
    }

    public void updateAnimation() {
        setAnimation(lightOn);
    }

    @Override
    public void turnOn() {
        isOn = true;
        setAnimation(lightOn);
    }

    @Override
    public void turnOff() {
        isOn = false;
        setAnimation(lightOff);
    }

    @Override
    public boolean isOn() {
        return this.isOn;
    }
}
