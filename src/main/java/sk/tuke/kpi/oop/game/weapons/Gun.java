package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm {

    public Gun(int minPoc, int maxPoc) {
        super(minPoc, maxPoc);
    }

    public Gun(int minPoc) {
        super(minPoc);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
