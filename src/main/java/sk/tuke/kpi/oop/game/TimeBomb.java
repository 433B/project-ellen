package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {
    private Animation bombAnimation;
    private Animation fireBombAnimation;
    private Animation boomAnimation;

    private float timer;
    private int removeactor;

    public TimeBomb() {
        this.timer = 15;

        bombAnimation = new Animation("sprites/bomb.png", 16, 16);
        fireBombAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        boomAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(bombAnimation);
    }

    public void activate() {
        if (timer > 0) {
            timer--;
            if (timer == 7) {
                setAnimation(fireBombAnimation);
            }
            if (timer == 0) {
                setAnimation(boomAnimation);
                this.getScene().removeActor(this);
            }
        }
    }

    public boolean isActivated() {
        if (timer <= 7) {
            return true;
        }
        else
        return false;
    }
}
