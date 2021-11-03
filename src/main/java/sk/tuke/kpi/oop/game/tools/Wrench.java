package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool {
    private Animation wrenchAnimation;

    public Wrench() {
        super(2);

        wrenchAnimation = new Animation("sprites/wrench.png", 16, 16);
        setAnimation(wrenchAnimation);
    }

    public void stormLight(DefectiveLight defectiveLight) {
        if (defectiveLight != null) {
            this.use();
            defectiveLight.turnOn();
        }
    }
}