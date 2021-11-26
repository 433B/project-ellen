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

//    public Fireable createBullet() {
//        return  null;
//    }

//    public Fireable fire() {
//        return null;
//    }

    void reload(int newAmmo) {
        if (newAmmo + now <= max) {
            max += newAmmo;
        }
    }
}
