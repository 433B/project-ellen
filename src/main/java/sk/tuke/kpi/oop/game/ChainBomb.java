package sk.tuke.kpi.oop.game;

public class ChainBomb extends TimeBomb {
    private float timer;
    private int s;
    private int v;

    public ChainBomb() {
        super();
        this.timer = 15;
        this.s = 50;
        this.v = 50;
    }

    public void minesWeeper() {
        if (isActivated() ) {
            s = getPosY();
            v = getPosX();
            activate();
        }
    }

    public void countTimer() {
        if (timer > 0) {
            timer--;
            this.activate();
        }
    }

}
