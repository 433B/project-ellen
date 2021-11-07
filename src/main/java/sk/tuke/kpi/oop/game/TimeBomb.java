package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

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
            new ActionSequence<>(new Wait<>(0), new Invoke<>(this::setKAK)).scheduleFor(this);
            new ActionSequence<>(new Wait<>(time), new Invoke<>(this::setAnim), new Invoke<>(this::delete)).scheduleFor(this);
        }

    }

    public void setKAK() {
        setAnimation(new Animation("sprites/bomb_activated.png", 16, 16, time / 6, Animation.PlayMode.LOOP_REVERSED));
    }

    public void setAnim() {
        setAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.125f, Animation.PlayMode.ONCE));
    }

    public void delete() {
        new When<>(
            () -> getAnimation().getCurrentFrameIndex() >= 7,
            new Invoke<>(() -> Objects.requireNonNull(getScene()).removeActor(this))
        ).scheduleFor(this);
    }

    public boolean isActivated() {
        return this.isOn;
    }
}

//        if (!isOn) {
//            this.isOn = true;
//            setAnimation(new Animation("sprites/bomb_activated.png", 16, 16, time / 6, Animation.PlayMode.LOOP_REVERSED));
//        }
//        if (isOn) {
//            this.time--;
//            if (this.time == 0) {
//                setAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.125f, Animation.PlayMode.ONCE));
//                new ActionSequence<>(
//                    new Wait<>(time),
//                    new Invoke<>(this::setAnim),
//                    new Invoke<>(this::delete)
//                ).scheduleFor(this);
//            }
//        }
//}
