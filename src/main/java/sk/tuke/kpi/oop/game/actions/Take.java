package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;

public class Take<A extends Keeper> extends AbstractAction<A> {
    public Take() {}
    @Override
    public void execute(float deltaTime) {
        if (getActor() == null) {
            setDone(true);
            return;
        }

        List<Actor> takeList = getActor().getScene().getActors();

        for (Actor actor : takeList) {
            if (actor instanceof Collectible && actor.intersects(getActor())) {
                if (addingItem(actor)) break;
            }
        }
        setDone(true);
    }

    private boolean addingItem(Actor actor) {
        try {
            getActor().getBackpack().add((Collectible) actor);
            getActor().getScene().removeActor(actor);
            return true;
        } catch (Exception ex) {
            getActor().getScene().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(1);
        }
        return false;
    }
}
