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
            updateAnimation();
        } else {
            updateAnimation();
        }
    }

    public void setElectricityFlow(boolean setElectricityFlow) {
        this.isOn = setElectricityFlow;
        updateAnimation();
    }

    public void updateAnimation() {
        if (this.isOn) {
            setAnimation(lightOn);
        }
        else {
            setAnimation(lightOff);
        }
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
