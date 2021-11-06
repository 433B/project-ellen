package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class TimeBomb extends AbstractActor {

    private boolean isOn;
    private float time;

    public TimeBomb(float time) {
        this.time = time;
        this.isOn = false;

        setAnimation(new Animation("sprites/bomb.png", 16, 16));
    }

    public void activate() {
        if (!isOn) {
            this.isOn = true;
            setAnimation(new Animation("sprites/bomb_activated.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_REVERSED));
        }
        if (isOn) {
            this.time--;
            if (this.time == 0) {
                setAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.25f, Animation.PlayMode.ONCE));
                new ActionSequence<>(
                    new Wait<>(2),
                    new Invoke<>(this::delete)
                ).scheduleFor(this);
            }
        }
    }

    public void delete() {
        if (getScene() != null) {
            getScene().removeActor(this);
        }
    }

    public boolean isActivated() {
        return this.isOn;
    }

//    public void detonation() {
//        setAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.2f, Animation.PlayMode.ONCE));
//        this.boom = true;
//    }
//
//    public boolean isBoom() {
//        return this.boom;
//    }

//    public void akBar() {
//        if (isActivated()) {
//            this.time--;
//
//            if (this.time == 0) {
//                this.boom = true;
//            }
//            if (isBoom() && isActivated()) {
//
//            }
//        }
//    }

//    public void waiting() {
//        new ActionSequence<>(
//            new Wait<>(2f)
//        ).scheduleFor(this);
//    }
}
