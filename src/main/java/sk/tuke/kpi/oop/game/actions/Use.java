package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<A extends  Actor> extends AbstractAction<A> {
    private final Usable<A> weapon;
    public Use(Usable<A> weapon) {
        this.weapon = weapon;
    }

    @Override
    public void execute(float deltaTime) {
        weapon.useWith(getActor());
        setDone(true);
    }

    public void scheduleForIntersectingWith(Actor mediatingActor) {
        Scene scene = mediatingActor.getScene();
        if (scene == null) return;
        Class<A> usingActorClass = weapon.getUsingActorClass();

        for (Actor actor : scene) {
            if (mediatingActor.intersects(actor) && usingActorClass.isInstance(actor)) {
                this.scheduleFor(usingActorClass.cast(actor));
                return;
            }
        }
    }
}
