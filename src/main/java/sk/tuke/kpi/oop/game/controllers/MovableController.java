package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {
    private Movable movable;
    private Move <Movable> move;
    private Disposable disposable;
    private Set<Input.Key> keyboard;


    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST)
    );

    public MovableController(Movable actor) {
        this.movable = actor;
        this.move = null;
        keyboard = new HashSet<>();
    }

    private void stopMoving() {
        if (move != null) {
            move.stop();
            disposable.dispose();
            move = null;
        }
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            keyboard.add(key);
            updateAnimation();
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            keyboard.remove(key);
            updateAnimation();
        }
    }

    private void updateAnimation() {
        Direction uholG = null;

        int a = 0;

        for (Input.Key kluc : keyboard) {
            if (a == 0)
                uholG = keyDirectionMap.get(kluc);
            if (a == 1)
                uholG = uholG.combine(keyDirectionMap.get(kluc));
            a++;
        }
        stopMoving();

        if (uholG != null) {
            move = new Move<>(uholG, Float.MAX_VALUE);
            disposable = move.scheduleFor(movable);
        }
    }
}
