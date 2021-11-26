package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;


public class KeeperController implements KeyboardListener {
    private Keeper keeper;

    public KeeperController(Keeper actor) {
        this.keeper = actor;
    }


    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (key.equals(Input.Key.ENTER)) {
            new Take<>().scheduleFor(keeper);
        }
        if (key == Input.Key.BACKSPACE) {
            new Drop<>().scheduleFor(keeper);
        }
        if (key == Input.Key.S) {
            new Shift<>().scheduleFor(keeper);
        }
        if (key == Input.Key.U) {
            for (Actor item : Objects.requireNonNull(this.keeper.getScene()).getActors()) {
                if (item instanceof Usable && this.keeper.intersects(item)) {
                    new Use<>((Usable<?>) item).scheduleForIntersectingWith(this.keeper);
                }
            }

        }

        if (key == Input.Key.B && keeper.getBackpack().peek() instanceof Usable) {
            Use<?> use = new Use<>((Usable<?>) keeper.getBackpack().peek());
            use.scheduleForIntersectingWith(keeper);
        }
    }

}
