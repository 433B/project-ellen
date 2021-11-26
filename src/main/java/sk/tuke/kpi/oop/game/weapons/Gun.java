package sk.tuke.kpi.oop.game.weapons;


import sk.tuke.kpi.oop.game.characters.Health;

public class Gun extends Firearm {

    public Gun(int minPoc, int maxPoc) {
        super(minPoc, maxPoc);
    }

    @Override
    public Health getHealth() {
        return null;
    }
}
