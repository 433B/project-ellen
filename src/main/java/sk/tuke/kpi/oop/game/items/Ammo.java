package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Ammo extends AbstractActor implements Usable<Ripley> {
    private Ripley ripley;

    public Ammo() {
        setAnimation(new Animation("sprites/ammo.png", 16, 16));
    }

    @Override
    public void useWith(Ripley actor) {
        if (ripley != null && ripley.getAmmo() > 0 && ripley.getAmmo() < 500 && this.getScene() != null && actor != null) {
            ripley.setAmmo(50);
            this.getScene().removeActor(this);
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
