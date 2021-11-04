package sk.tuke.kpi.oop.game;

public class ChainBomb extends TimeBomb {
    private float timer;
//    private int range;

    public ChainBomb() {
        super();
        this.timer = 15;
//        this.range = 50;
    }

    public void countTimer() {
        if (timer > 0) {
            timer--;
            this.activate();
        }
    }
//
//    public void minesWeeper() {
//        if (this.range == getPosX() && this.range == getPosY()) {
//            this.activate();
//        }
//    }

}
