package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;

public class Take<A extends Keeper> extends AbstractAction<A> {
    private List<Actor> takeList;
    public Take() {

    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() == null ) {
            setDone(true);
            return;
        }

        takeList = getActor().getScene().getActors();

        for (Actor actor: takeList) {
            if (actor instanceof Collectible && actor.intersects(getActor())) {
                try {
                    getActor().getBackpack().add((Collectible) actor);
                    getActor().getScene().removeActor(actor);
                    break;
                }
                catch (Exception e) {
                    getActor().getScene().getOverlay().drawText(e.getMessage(), 0, 0).showFor(1);
                }
            }
        }
    }
}
