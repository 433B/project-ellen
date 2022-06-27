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
    private final Movable movable;
    private Move <Movable> move;
    private Disposable disposable;
    private final Set<Input.Key> keyboard;


    private final Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
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
        Direction angel = null;

        int a = 0;
        for (Input.Key key : keyboard) {
            switch (a) {
                case 0:
                    angel = keyDirectionMap.get(key);
                    break;
                case 1:
                    angel = angel.combine(keyDirectionMap.get(key));
                    break;
            }
            a++;
        }
        stopMoving();

        if (angel != null) {
            move = new Move<>(angel, Float.MAX_VALUE);
            disposable = move.scheduleFor(movable);
        }
    }
}
