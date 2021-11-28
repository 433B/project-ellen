package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;

public class Take<A extends Keeper> extends AbstractAction<A> {
    public Take() {

    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() == null ) {
            setDone(true);
            return;
        }

        List<Actor> takeList = getActor().getScene().getActors();

        for (Actor actor: takeList) {
            if (actor instanceof Collectible && actor.intersects(getActor())) {
                try {
                    getActor().getBackpack().add((Collectible) actor);
                    getActor().getScene().removeActor(actor);
                    break;
                }
                catch (Exception ex) {
                    getActor().getScene().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(1);
                }
            }
        }
        setDone(true);
    }
}
