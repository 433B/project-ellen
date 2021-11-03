package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    public Reactor actor;
    private int second;

    public PerpetualReactorHeating(int i) {
        this.second = i;
    }

    @Override
    public void execute(float deltaTime) {
        this.actor = getActor();
        actor.increaseTemperature(second);
    }

    public Disposable scheduleFor(Reactor actor) {
        return actor.getScene().scheduleAction(this, actor);
    }
}
