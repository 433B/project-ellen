package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {
    private double temperature;
    private double damage;
    private boolean onOrOff = false;

    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokeAnimation;
    private Animation offAnimation;
    private Animation extinguished;

    public Reactor() {
        this.temperature = 0;
        this.damage = 0.0;

        turnOff();
    }

    public void getTemperature() {
        updateAnimation();
    }

    public void getDamage() {
        updateAnimation();
    }

    public void increaseTemperature(int add) {
        if (!onOrOff == true && add > 0) {
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
        if (!onOrOff == true && sub > 0) {
            return;
        }
        if (sub <= -1) {
            sub = 0;
        }
        this.temperature = temperature - sub;

        if (temperature < 0) {
            temperature = 0;
        }
        updateAnimation();
    }

    public void updateAnimation() {
        if (damage == 0.0) {
            normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.4f, Animation.PlayMode.LOOP_REVERSED);
            setAnimation(normalAnimation);
        }
        if (damage >= 33.0 && 66.0 > damage) {
            normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_REVERSED);
            setAnimation(normalAnimation);
        }
        if (damage >= 66.0) {
            hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_REVERSED);
            setAnimation(hotAnimation);
        }
        if (damage >= 100.0) {
            brokeAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
            setAnimation(brokeAnimation);
        }
    }

    public void repairWith(Hammer hammer) {
        hammer.use();
        if (damage > 0 && damage < 100) {
            if (damage < 50) {
                damage = damage - damage;
            } else
                damage = damage - 50;

            this.temperature = this.damage * 6000 / 100;
        }
    }

    public void turnOn() {
        onOrOff = true;
        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.4f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(normalAnimation);
    }

    public void turnOff() {
        onOrOff = false;
        offAnimation = new Animation("sprites/reactor.png");
        setAnimation(offAnimation);

        if (damage >= 66) {
            hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.0f, Animation.PlayMode.LOOP_REVERSED);
            setAnimation(hotAnimation);
        }
    }

    public boolean isRunning() {
        if (onOrOff == true) {
            return true;
        }
        if (onOrOff == false) {
            return false;
        }
        return false;
    }

    public void addLight(Light light) {
        if (onOrOff == true) {
            light.lightOn();
        }
    }

    public void removeLight(Light light) {
        if (onOrOff == false && damage == 100) {
            light.lightOff();
        }
    }

    public void extinguishWith(FireExtinguisher fireExtinguisher) {
        if (this.damage == 100) {
            fireExtinguisher.use();
            this.temperature = 4000;

            extinguished = new Animation("sprites/reactor_extinguished.png", 80, 80);
            setAnimation(extinguished);
        }
    }
}



