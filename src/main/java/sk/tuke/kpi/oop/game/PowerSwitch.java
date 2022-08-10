package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch
    extends AbstractActor {
    private final Switchable device;

    public PowerSwitch(Switchable device) {
        this.device = device;
        setAnimation(
            new Animation(
                "sprites/switch.png"));
    }

    public void switchOn() {
        if (device != null) this.device.turnOn();
    }

    public void switchOff() {
        if (device != null) this.device.turnOff();
    }

    public void setTint() {
        if (!device.isOn()) getAnimation().setTint(Color.GRAY);
    }

    public Switchable getDevice() {
        return this.device;
    }
}
