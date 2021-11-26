package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;

public class Take<A extends  Keeper> extends AbstractAction<Keeper> {


    private A ripley;

    public Take() {
    }

    public Take(A ripley) {
        this.ripley = ripley;
    }

    @Override
    public void execute(float deltaTime) {
        try {
            for (Actor weapon : Objects.requireNonNull(Objects.requireNonNull(getActor()).getScene()).getActors()) {
                if (weapon instanceof Collectible && weapon != this.ripley && getActor().intersects(weapon)) {
                    getActor().getBackpack().add((Collectible) weapon);
                    Objects.requireNonNull(getActor().getScene()).removeActor(weapon);
                    break;
                }
            }
        } catch (IllegalStateException error) {
            Objects.requireNonNull(Objects.requireNonNull(getActor()).getScene()).getOverlay().drawText(error.getMessage(), 50, 50).showFor(2);
        }
        setDone(true);
    }
}
