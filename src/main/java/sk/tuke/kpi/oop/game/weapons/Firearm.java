package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.characters.Alive;

public abstract class Firearm extends AbstractActor implements Alive, Movable, Fireable {
    private int max;
    private int now;

    public Firearm(int minPoc, int maxPoc) {
        this.max = maxPoc;
        this.now = minPoc;
    }

    public Firearm(int maxPoc) {
        max = maxPoc;
    }

    protected abstract Fireable createBullet();

    public int getAmmo() {
        return now;
    }

    public void reload(int newAmmo) {
        if ((getAmmo() + newAmmo) < max) {
            now = now + newAmmo;
        }
        else {
            now = max;
        }

    }

    public Fireable fire() {
        if (now != 0) {
            now--;
            return createBullet();
        }
        else {
            return null;
        }
    }
}
