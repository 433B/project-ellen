package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;


public class Door extends AbstractActor implements Usable<Actor>, Openable {
    private Animation openDoorAnimation;
    private Animation closeDoorAnimation;

    private boolean isOpen;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public Door() {
        this.isOpen = false;

        openDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
        closeDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
        setAnimation(closeDoorAnimation);
        getAnimation().play();
    }

    @Override
    public void useWith(Actor actor) {
        if (isOpen) {
            close();
        }
        else {
            open();
        }
    }

    @Override
    public void open() {
        if(!isOpen()) {
            isOpen = true;
            setAnimation(openDoorAnimation);
            Objects.requireNonNull(getScene()).getMessageBus().publish(DOOR_OPENED, this);
            openDoorAnimation.play();
            openDoorAnimation.stop();
        }
    }

    @Override
    public void close() {
        if (isOpen()) {
            isOpen = false;
            setAnimation(closeDoorAnimation);
            Objects.requireNonNull(getScene()).getMessageBus().publish(DOOR_CLOSED, this);
            closeDoorAnimation.play();
            closeDoorAnimation.stop();
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
    }


    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }
}
