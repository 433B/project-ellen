package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool {
    private Animation wrenchAnimation;

    private Wrench wrench;

    public Wrench(int numerosity) {
        super(numerosity);

        wrenchAnimation = new Animation("sprites/wrench.png", 16, 16);
        setAnimation(wrenchAnimation);
    }

    public void stormLight(DefectiveLight defectiveLight) {
        if (defectiveLight != null) {
            this.useWith(wrench);
            defectiveLight.turnOn();
        }
    }

    Wrench() {
        super(2);
    }
}
