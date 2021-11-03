package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class DefectiveLight extends Light implements Repairable {
    private int number;
 
 
    private Animation lightOn;
    private Animation lightOff;

    public DefectiveLight() {
        super();

        lightOff = new Animation("sprites/light_off.png", 16, 16);
        lightOn = new Animation("sprites/light_on.png", 16, 16);
    }

    public void randomNumber() {
        if (isOn() ) {
            number = (int) (Math.random() * 20);
            if (number == 5 || number == 10) {
                setAnimation(lightOn);
            }
            else setAnimation(lightOff);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::randomNumber)).scheduleFor(this);
    }

    @Override
    public boolean repair(AbstractTool tool) {
        return false;
    }
}
