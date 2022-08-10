package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating
    extends AbstractAction<Reactor> {
    private final int second;

    public PerpetualReactorHeating(int i) {
        this.second = i;
    }
    @Override
    public void execute(float deltaTime) {
        if (getActor() != null) {
            getActor().increaseTemperature(second);
        }
    }
}
