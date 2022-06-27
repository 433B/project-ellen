package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int now;
    private final int max;

    public Firearm(int current, int max) {
        this.now = current;
        this.max = max;
    }

    public Firearm(int max) {
        this.now = max;
        this.max = max;
    }

    public void decreaseAmmo(int amount) {
        if (now != 0) now -= amount;
        if (now < 0) now = 0;
    }

    protected abstract Fireable createBullet();

    public void reload(int newAmmo) {
        if (getAmmo() + newAmmo < max) now += newAmmo;
        else now = max;
    }

    public Fireable fire() {
        if (now != 0) {
            now--;
            return createBullet();
        }
        else return null;
    }

    public int getAmmo() {
        return now;
    }

    public void setCurrent(int current) {
        this.now = current;
    }
}
