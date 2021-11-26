package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;

public class Drop<A extends Keeper> extends AbstractAction<Keeper> {


    public Drop() {

    }

    @Override
    public void execute(float deltaTime) {
        try {
            Collectible collectible = Objects.requireNonNull(getActor()).getBackpack().peek();
            if (collectible != null) {
                Objects.requireNonNull(getActor().getScene()).addActor(
                    collectible,
                    getActor().getPosY() + collectible.getHeight() / 2,
                    getActor().getPosX() + collectible.getWidth() / 2
                );
            }
            assert collectible != null;
            getActor().getBackpack().remove(collectible);
        }  catch (IllegalStateException error) {
            Objects.requireNonNull(Objects.requireNonNull(getActor()).getScene()).getOverlay().drawText(error.getMessage(), 50, 50).showFor(2);
        }
        setDone(true);
    }
}
