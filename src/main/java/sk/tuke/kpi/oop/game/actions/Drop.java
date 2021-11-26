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
        Collectible weapon = Objects.requireNonNull(getActor()).getBackpack().peek();
        if (getActor().getScene() != null && getActor() != null && getActor().getBackpack().peek() != null && !isDone() && weapon != null) {
            getActor().getScene().addActor(weapon,
                (getActor().getPosX() + weapon.getWidth() / 2),
                (getActor().getPosY() + weapon.getHeight() / 2));
            getActor().getBackpack().remove(weapon);
        }
    }
}
