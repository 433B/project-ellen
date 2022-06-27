package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;

public class Drop<K extends Keeper> extends AbstractAction<K> {
    @Override
    public void execute(float deltaTime) {
        try {
            Collectible weapon = getActor().getBackpack().peek();
            dropWeapon(weapon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDone(true);
    }

    private void dropWeapon(Collectible weapon) {
        if (weapon != null) {
            getActor().getScene().addActor(
                weapon,
                getActor().getPosX() + weapon.getWidth() / 2,
                getActor().getPosY() + weapon.getHeight() / 2
            );
        }
        if (weapon != null) {
            getActor().getBackpack().remove(weapon);
        }
    }
}
