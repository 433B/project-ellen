package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private double temperature;
    private double damage;
    private boolean isRunning = false;
    private Set<EnergyConsumer> devices;

    private Animation normalAnimation;
    private Animation fastNormal;
    private Animation hotAnimation;
    private Animation brokeAnimation;
    private Animation offAnimation;
    private Animation reactor_extinguished;

    public Reactor() {
        devices = new HashSet<>();

        offAnimation = new Animation("sprites/reactor.png");
        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.4f, Animation.PlayMode.LOOP_REVERSED);
        fastNormal = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_REVERSED);
        hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_REVERSED);
        brokeAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        reactor_extinguished = new Animation("sprites/reactor_extinguished.png", 80, 80, 0.4f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(offAnimation);
        updateAnimation();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

    public double getTemperature() {
        return this.temperature;
    }

    public double getDamage() {
        return this.damage;
    }

    public void increaseTemperature(int add) {
        if (!this.isRunning && add > 0) {
            return;
        }
        if (add > 0) {
            this.temperature = temperature + add;
            if (temperature >= 2000) {
                this.damage = 100 * temperature / 6000;
            }
            if (damage > 100) {
                damage = 100;
                isRunning = false;
            }
            if (temperature > 6000) {
                temperature = 6000;
                isRunning = false;
            }
        }
    }

    public void decreaseTemperature(int sub) {
        if (!this.isRunning && sub < 0) {
            return;
        }

        if (sub > 0) {
            this.temperature = temperature - sub;
            if (temperature < 0) {
                temperature = 0;
            }
        }
    }

    public void updateAnimation() {
        if (damage == 0.0) {
            setAnimation(normalAnimation);
        }
        if (damage >= 33.0) {
            setAnimation(fastNormal);
        }
        if (damage >= 66.0) {
            setAnimation(hotAnimation);
        }
        if (damage == 100) {
            setAnimation(brokeAnimation);
        }
    }

    @Override
    public boolean repair() {
        if (damage < 50) {
            damage = 0.0;
        } else
            damage = damage - 50;
        this.temperature = this.damage * 6000 / 100;
        return true;
    }

    public void extinguih() {
        if (damage == 100) {
            this.temperature = 4000;
            setAnimation(reactor_extinguished);
        }
    }

    public void turnOn() {
        if (!this.isRunning) {
            this.isRunning = true;
            setAnimation(normalAnimation);
        }
    }

    public void turnOff() {
        if (this.isRunning) {
            this.isRunning = false;
            setAnimation(offAnimation);
        }
    }

    public boolean isOn() {
        return isRunning;
    }

    public void addDevice(EnergyConsumer device) {
        if (device != null) {
            this.devices.add(device);
            device.setElectricityFlow(isRunning);
        }
    }

    public void removeDevice(EnergyConsumer device) {
        if (device != null) {
            device.setElectricityFlow(false);
            this.devices.remove(device);
        }
    }
}
