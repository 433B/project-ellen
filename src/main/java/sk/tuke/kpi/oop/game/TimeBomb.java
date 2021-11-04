package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.actions.ActionSequence;

public class TimeBomb extends AbstractActor {
    private Animation bombAnimation;
    private Animation fireBombAnimation;
    private Animation boomAnimation;

    private float timer;
    private boolean isOn;

    public TimeBomb() {
        this.timer = 15;

        bombAnimation = new Animation("sprites/bomb.png", 16, 16);
        fireBombAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        boomAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED);
        setAnimation(bombAnimation);
    }

    public void activate() {
        setAnimation(bombAnimation);
        this.isOn = true;
    }

    public boolean isActivated() {
        setAnimation(fireBombAnimation);
        return isOn;
    }

    public void arabHaveDetonator() {
        if (isActivated()) {
            if (timer >= -4) {
                timer--;
                if (timer == 0) {
                    setAnimation(boomAnimation);
                }
                if (timer == -5 && getScene() != null) {
                    getScene().removeActor(this);
                }
            }
        }
    }

}
