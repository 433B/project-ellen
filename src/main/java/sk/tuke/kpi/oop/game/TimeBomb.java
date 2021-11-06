package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class TimeBomb extends AbstractActor {
    private Animation fireBombAnimation;
    private Animation boomAnimation;

    private boolean isOn;
    private boolean boom;
    private float time;

    public TimeBomb(float time) {
        this.time = time;
        this.isOn = false;
        this.boom = false;

        fireBombAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        boomAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.2f, Animation.PlayMode.ONCE);
        setAnimation(new Animation("sprites/bomb.png", 16, 16));
    }


    public void activate() {
        setAnimation(fireBombAnimation);
        this.isOn = true;
    }

    public boolean isActivated() {
        return this.isOn;
    }

    public void akBar() {
        if (isActivated()) {
            this.time--;
            if (this.time == 0) {
                this.boom = true;
                setAnimation(boomAnimation);
            } else this.boom = false;
            if (this.time == -5) {
                Objects.requireNonNull(getScene()).removeActor(this);
            }
        }
    }

    public boolean isBoom() {
        return boom;
    }
}
