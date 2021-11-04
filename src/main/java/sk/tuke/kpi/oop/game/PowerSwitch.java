package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {
    private Animation switchAnimation;

    private Switchable device;

    public PowerSwitch(Switchable device) {
        if (device != null) {
            this.device = device;
        }

        switchAnimation = new Animation("sprites/switch.png", 16, 16, 0.4f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(switchAnimation);
    }

    public void switchOn() {
        if (!device.isOn()) {
            device.turnOn();
        }
    }

    public void switchOff() {
        if (device.isOn()) {
            device.turnOff();
        }
    }

    public void setTint() {
        if (!device.isOn()) {
            getAnimation().setTint(Color.GRAY);
        }
    }
}
