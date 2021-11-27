package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;
import java.util.Objects;

public class Take<A extends Keeper> extends AbstractAction<A> {
    private A takeActor;
    private  List<Actor> takeList;

    public Take() {

    }

    public Take(A take) {
        this.takeActor = take;
    }

    @Override
    public void execute(float deltaTime) {
        if (!isDone() && getActor() != null || Objects.requireNonNull(getActor()).getScene() != null) {
            takeList = Objects.requireNonNull(getActor().getScene()).getActors();
            for (Actor actor : takeList) {
                if (actor instanceof Collectible && actor.intersects(getActor()) && this.takeActor != actor) {
                    try {
                        getActor().getBackpack().add(((Collectible) actor));
                        getActor().getScene().removeActor(actor);
                        break;
                    } catch (IllegalStateException exception) {
                        getActor().getScene().getOverlay().drawText(exception.getMessage(), 0, 0).showFor(2);
                    }
                }
            }
            setDone(true);
        }
    }
}
