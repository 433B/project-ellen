package sk.tuke.kpi.oop.game.tools;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends BreakableTool {

    private Animation extinguisherAnimation;

    public FireExtinguisher() {
        super(1);

        extinguisherAnimation = new Animation("sprites/extinguisher.png", 16, 16);
        setAnimation(extinguisherAnimation);
    }
}
