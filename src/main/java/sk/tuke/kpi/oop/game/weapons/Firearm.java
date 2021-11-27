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

    public int getAmmo() {
        return now;
    }

    public void reload(int newAmmo) {
        this.now += newAmmo;
        if (this.now > this.max) {
            this.now = this.max;
        }
    }

    protected abstract Fireable createBullet();

    public Fireable fire() {
        if (this.now >= 1) {
            this.now -= 1;
            return createBullet();
        } else {
            return null;
        }
    }
}
