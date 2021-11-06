package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private int temperature;
    private int damage;
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
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getDamage() {
        return this.damage;
    }

    public void increaseTemperature(int add) {
        if (!this.isRunning && add > 0) {
            return;
        }
        if (add > 0) {
            if (damage >= 33 && damage <= 66) {
                temperature = (int) Math.round(temperature + (add * 1.5));
            } else if (damage > 66) {
                temperature = Math.round(temperature + (add * 2));
            } else {
                temperature = temperature + add;
            }
            if (temperature > 2000) {
                this.damage = 100 * temperature / 6000;
            }
            if (damage > 100) {
                damage = 100;
                isRunning = false;
            }
        }
        updateAnimation();
    }

    public void decreaseTemperature(int sub) {
        if (!this.isRunning && sub < 0) {
            return;
        }

        if (sub > 0 && isRunning) {
            if (damage >= 50 && damage < 100) {
                temperature = temperature - sub / 2;
            }
            if (damage < 50) {
                this.temperature = temperature - sub;
            }
            if (temperature < 0) {
                temperature = 0;
            }
        }
        updateAnimation();
    }

    public void updateAnimation() {
        if (isRunning) {
            if (damage == 0.0) {
                setAnimation(normalAnimation);
            }
            if (damage >= 33.0) {
                setAnimation(fastNormal);
            }
            if (damage >= 66.0) {
                setAnimation(hotAnimation);
            }
        } else {
            if (damage == 100) {
                setAnimation(brokeAnimation);
            }
            if (damage < 100) {
                setAnimation(offAnimation);
            }
        }
    }

    @Override
    public boolean repair() {
        if (getDamage() > 0 && getDamage() < 100 && isRunning) {
            int sub = damage - 50;
            if (sub > 0) {
                temperature = (int) Math.round((sub / 0.025) + 2000);
                damage -= 50;
            } else {
                temperature = 2000;
                damage = 0;
            }
            if (damage < 0) {
                damage = 0;
            }
            updateAnimation();
            return true;
        }
        return false;
    }

    public boolean extinguish() {
        if (damage == 100 && !isRunning) {
            this.temperature = 4000;
            setAnimation(reactor_extinguished);
        }
        return this.getTemperature() == 4000;
    }

    public void turnOn() {
        if (!this.isRunning) {
            this.isRunning = true;
            setAnimation(normalAnimation);
        }
        if (this.isOn() && damage != 100) {
            for (EnergyConsumer dev : devices) {
                dev.setPowered(true);
            }
        }
    }

    public void turnOff() {
        if (this.isRunning) {
            this.isRunning = false;
            setAnimation(offAnimation);
        }
        if (!this.isOn() || getDamage() == 100 || !this.isOn() && getDamage() == 100) {
            for (EnergyConsumer dev : devices) {
                dev.setPowered(false);
            }
        }
    }

    public boolean isOn() {
        return isRunning;
    }

    public void addDevice(EnergyConsumer device) {
        if (isOn() && damage != 100) {
            for (EnergyConsumer dev : devices) {
                dev.setPowered(true);
            }
        }
        if (device != null && this.isRunning) {
            device.setPowered(true);
            this.devices.add(device);
        }
    }

    public void removeDevice(EnergyConsumer device) {
        if (!isOn() || damage == 100 || !isOn() && getDamage() == 100) {
            for (EnergyConsumer dev : devices) {
                dev.setPowered(false);
            }
        }
        if (device != null && !this.isRunning) {
            device.setPowered(false);
            this.devices.remove(device);
        }
    }
}
