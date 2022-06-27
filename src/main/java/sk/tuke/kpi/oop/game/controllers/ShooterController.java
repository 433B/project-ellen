package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;

public class ShooterController implements KeyboardListener {
    private final Armed armed;

    public ShooterController(Armed shooterActor) {
        this.armed = shooterActor;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (key == Input.Key.SPACE && armed != null) {
            new Fire<>().scheduleFor(armed);
        }
    }
}
