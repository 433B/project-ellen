package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.tools.BreakableTool;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private double temperature;
    private double damage;
    private boolean isRunning = false;
//    private EnergyConsumer device;
    private Set<EnergyConsumer> devices;

    private Animation normalAnimation;
    private Animation fastNormal;
    private Animation hotAnimation;
    private Animation brokeAnimation;
    private Animation offAnimation;
    private Animation extinguished;

    public Reactor() {
        this.temperature = 0;
        this.damage = 0.0;
        devices = new HashSet<>();

        offAnimation = new Animation("sprites/reactor.png");
        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.4f, Animation.PlayMode.LOOP_REVERSED);
        fastNormal = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_REVERSED);
        hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_REVERSED);
        brokeAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        extinguished = new Animation("sprites/reactor_extinguished.png", 80, 80);
        setAnimation(offAnimation);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

    public double getTemperature() {
        return temperature;
    }

    public double getDamage() {
        return damage;
    }

    public void increaseTemperature(int add) {
        if (!this.isRunning && add > 0) {
            return;
        }
        this.temperature = temperature + add;
        if (temperature >= 2000) {
            this.damage = 100 * temperature / 6000;
        }
        if (damage > 100) {
            damage = 100;
        }
        if (temperature > 6000) {
            temperature = 6000;
        }
        updateAnimation();
    }

    public void decreaseTemperature(int sub) {
        if (!this.isRunning && sub > 0) {
            return;
        }
//        if (sub <= -1) {
//            sub = 0;
//        }
        this.temperature = temperature - sub;
        if (temperature < 0) {
            temperature = 0;
        }
        updateAnimation();
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
        if (damage >= 100.0) {
            setAnimation(brokeAnimation);
        }
    }

    public void repair(BreakableTool breakableTool) {
        breakableTool.use();
        if (damage > 0 && damage < 100) {
            if (damage < 50) {
                damage = damage - damage;
            } else
                damage = damage - 50;
            this.temperature = this.damage * 6000 / 100;
        }
    }

    public void extinguih(BreakableTool breakableTool) {
        if (this.damage == 100) {
            breakableTool.use();
            this.temperature = 4000;
            setAnimation(extinguished);
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
        if (isRunning) {
            return true;
        }
        else
            return false;
    }

    public void addDevice(EnergyConsumer device) {
        if (device instanceof EnergyConsumer) {
//            this.device = device;
            this.devices.add(device);
            if (isRunning) {
                ((EnergyConsumer) device).setElectricityFlow(true);
            } else {
                ((EnergyConsumer) device).setElectricityFlow(false);
            }
        }
    }

    public void removeDevice(EnergyConsumer device) {
        if (device instanceof EnergyConsumer) {
            ((EnergyConsumer) device).setElectricityFlow(false);
            this.devices.remove(device);
        }
    }

    @Override
    public boolean repair(AbstractTool tool) {
        return false;
    }
}
