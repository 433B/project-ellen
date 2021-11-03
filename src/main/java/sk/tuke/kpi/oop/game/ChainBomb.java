package sk.tuke.kpi.oop.game;

public class ChainBomb extends TimeBomb {
    private float timer;

    public ChainBomb() {
        super();
        this.timer = 15;
    }


    public void countTimer() {
        if (timer > 0) {
            timer--;
            this.activate();
        }
    }

}
