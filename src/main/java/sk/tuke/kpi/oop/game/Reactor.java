package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    public static final int MAX = 100;
    private int temperature;
    private int damage;
    private boolean isRunning = false;
    private final Set<EnergyConsumer> devices;
    private final Animation normalAnimation;
    private final Animation fastNormal;
    private final Animation hotAnimation;
    private final Animation brokeAnimation;
    private final Animation offAnimation;
    private final Animation reactor_extinguished;

    public Reactor() {
        devices = new HashSet<>();

        offAnimation = new Animation("sprites/reactor.png");
        normalAnimation = new Animation("sprites/reactor_on.png",
            80, 80,
            0.4f, Animation.PlayMode.LOOP_REVERSED);

        fastNormal = new Animation("sprites/reactor_on.png",
            80, 80,
            0.1f, Animation.PlayMode.LOOP_REVERSED);

        hotAnimation = new Animation("sprites/reactor_hot.png",
            80, 80,
            0.1f, Animation.PlayMode.LOOP_REVERSED);

        brokeAnimation = new Animation("sprites/reactor_broken.png",
            80, 80,
            0.1f, Animation.PlayMode.LOOP_PINGPONG);

        reactor_extinguished = new Animation("sprites/reactor_extinguished.png",
            80, 80,
            0.4f, Animation.PlayMode.LOOP_REVERSED);
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
        if (add > 0 && isRunning) {
            rulesForIncrease(add);
            if (temperature > 2000) {
                this.damage = 100 * temperature / 6000;
            }
            if (damage >= 100) {
                damage = 100;
                for (EnergyConsumer intro : devices) {
                    intro.setPowered(false);
                }
                isRunning = false;
            }
        }
        updateAnimation();
    }

    private void rulesForIncrease(int add) {
        if (damage >= 33 && damage <= 66) {
            temperature = (int) Math.round(temperature + (add * 1.5));
        } else if (damage > 66) {
            temperature = Math.round(temperature + (add * 2));
        } else {
            temperature = temperature + add;
        }
    }

    public void decreaseTemperature(int sub) {
        if (!this.isRunning && sub < 0) {
            return;
        }

        rulesForDecrease(sub);
        updateAnimation();
    }

    private void rulesForDecrease(int sub) {
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
    }

    public void updateAnimation() {
        if (isRunning) {
            switch (damage) {
                case 0:
                    setAnimation(normalAnimation);
                    break;
                case 33:
                    setAnimation(fastNormal);
                    break;
                case 66:
                    setAnimation(hotAnimation);
                    break;
            }
        } else {
            if (damage == MAX) setAnimation(brokeAnimation);
            if (damage < MAX) setAnimation(offAnimation);
        }
    }

    @Override
    public boolean repair() {
        if (getDamage() > 0 && getDamage() < MAX && isRunning) {
            int sub = damage - 50;
            rulesForRepair(sub);

            if (damage < 0) damage = 0;
            updateAnimation();
            return true;
        }
        return false;
    }

    private void rulesForRepair(int sub) {
        if (sub > 0) {
            temperature = (int) Math.round((sub / 0.025) + 2000);
            damage -= 50;
        } else {
            temperature = 2000;
            damage = 0;
        }
    }

    public boolean extinguish() {
        if (damage == MAX) {
            this.temperature = 4000;
            setAnimation(reactor_extinguished);
        }
        return this.getTemperature() == 4000;
    }

    public void turnOn() {
        if (!this.isRunning && damage != MAX) {
            for (EnergyConsumer intro : devices) {
                intro.setPowered(true);
            }
            this.isRunning = true;
            setAnimation(normalAnimation);
        }
    }

    public void turnOff() {
        if (this.isRunning) {
            for (EnergyConsumer intro : devices) {
                intro.setPowered(false);
            }
            this.isRunning = false;
            setAnimation(offAnimation);
        }
    }

    public boolean isOn() {
        return isRunning;
    }

    public void addDevice(EnergyConsumer device) {
        if (device != null) {
            for (EnergyConsumer intro : devices) {
                intro.setPowered(true);
            }
            device.setPowered(isRunning);
            this.devices.add(device);
        }
    }

    public void removeDevice(EnergyConsumer device) {
        if (device != null) {
            for (EnergyConsumer intro : devices) {
                intro.setPowered(false);
            }
            device.setPowered(false);
            this.devices.remove(device);
        }
    }
}
