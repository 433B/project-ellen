package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {
    private Animation bombAnimation;
    private Animation fireBombAnimation;
    private Animation boomAnimation;

    private boolean isOn;
    private float time;

    public TimeBomb(float time) {
        this.time = time;

        bombAnimation = new Animation("sprites/bomb.png", 16, 16);
        fireBombAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        boomAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(bombAnimation);
    }

    public void activate() {
        this.isOn = true;
        setAnimation(bombAnimation);
    }

    public boolean isActivated() {
        setAnimation(fireBombAnimation);
        return this.isOn;
    }

    public void akBar() {
        if (isOn) {
            this.time--;
        }
        if (this.time == 0) {
            setAnimation(boomAnimation);
        }
        if (this.time == -5 && getScene() != null) {
            getScene().removeActor(this);
        }
    }
}
