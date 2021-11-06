package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {
    private Animation fireBombAnimation;
    private Animation boomAnimation;

    private boolean isOn;
    private float time;

    public TimeBomb(float time) {
        this.time = time;
        this.isOn = false;

        fireBombAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        boomAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(new Animation("sprites/bomb.png", 16, 16));
    }

    public void activate() {
        this.isOn = true;
        updateAnimation();
    }

    public boolean isActivated() {
        return this.isOn;
    }

    public void akBar() {
        if (isActivated()) {
            this.time--;
            if (this.time == 0) {
                updateAnimation();
            }
            if (this.time == -40 && getScene() != null) {
                getScene().removeActor(this);
            }
        }
    }

    public void updateAnimation() {
        if (isActivated() ) {
            setAnimation(fireBombAnimation);
        }
        if (this.time == 0) {
            setAnimation(boomAnimation);
        }
    }
}
