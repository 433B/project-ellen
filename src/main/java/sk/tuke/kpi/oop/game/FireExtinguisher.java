package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends AbstractActor {
    private int counter;

    private Animation extinguisherAnimation;

    public FireExtinguisher() {
        this.counter = 1;

        extinguisherAnimation = new Animation("sprites/extinguisher.png", 16, 16);
        setAnimation(extinguisherAnimation);
    }

    public void use() {
        this.counter--;

        if (this.counter == 0) {
            this.getScene().removeActor(this);
        }
    }

    public int gerCounter() {
        return this.counter;
    }
}
