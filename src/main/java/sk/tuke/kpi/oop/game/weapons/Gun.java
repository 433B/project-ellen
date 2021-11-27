package sk.tuke.kpi.oop.game.weapons;


import sk.tuke.kpi.oop.game.characters.Health;

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

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Health getHealth() {
        return null;
    }
}
