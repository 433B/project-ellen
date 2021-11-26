package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;
import java.util.Objects;

public class Take<A extends  Keeper> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime) {
        Scene scene = Objects.requireNonNull(getActor()).getScene();
        List<Actor> itemList;

        if (!isDone() && (getActor().getScene() == null || getActor() != null)) {
            itemList = Objects.requireNonNull(getActor().getScene()).getActors();
            for (Actor weapon : itemList) {
                if (weapon instanceof Collectible && weapon.intersects(getActor())) {
                    try {
                        getActor().getBackpack().add(((Collectible) weapon));
                        assert scene != null;
                        scene.removeActor(weapon);
                        break;
                    } catch (IllegalStateException exception) {
                        assert scene != null;
                        scene.getOverlay().drawText(exception.getMessage(), 0, 0).showFor(2);
                    }
                }
            }
            setDone(true);
        }
    }
}
