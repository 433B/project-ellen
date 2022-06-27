package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.Objects;

public class LevelFailed extends AbstractActor implements SceneListener {
    private Animation failedLevelAnimation;

    private Ripley ripley = new Ripley();

    public LevelFailed() {
        failedLevelAnimation = new Animation("sprites/popop_level_failed.png", 288, 128);
        setAnimation(failedLevelAnimation);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(this::shooting)
        ).scheduleFor(this);
    }

    private void shooting() {
        for (Actor actor : Objects.requireNonNull(getScene()).getActors()) {
            if (this.intersects(actor) && (actor instanceof Ripley)) {

//            if (this.intersects(actor) && (actor instanceof Alive)) {
//                ((Alive) actor).getHealth().drain(gunDamage);
            }
        }
    }

}
