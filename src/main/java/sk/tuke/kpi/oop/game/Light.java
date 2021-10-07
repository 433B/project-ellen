package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor {

    private Animation lightOn;
    private Animation lightOff;

    private boolean setOffOn;

    public Light() {
        lightOff = new Animation("sprites/light_off.png", 16, 16);
        lightOn = new Animation("sprites/light_on.png", 16, 16);
        setAnimation(lightOff);
    }

    public void toggle() {
        if (setOffOn == true) {
            setOffOn = false;
            setAnimation(lightOff);
        } else {
            setOffOn = true;
            setAnimation(lightOn);
        }
    }

    private boolean setElectricityFlow() {
        if (setOffOn = true) {
            lightOn();
            return true;
        } else {
            lightOff();
            return false;
        }
    }

    public void lightOn() {
        setOffOn = true;
        setAnimation(lightOn);
    }

    public void lightOff() {
        setOffOn = false;
        setAnimation(lightOff);
    }
}
