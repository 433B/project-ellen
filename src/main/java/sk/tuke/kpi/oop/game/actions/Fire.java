package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

import java.util.Objects;

public class Fire <A extends Armed> extends AbstractAction<A> {
    public Fire() {

    }

    @Override
    public void execute(float deltaTime) {
        A actorExecute = getActor();

        if (actorExecute != null && !isDone()) {
            Fireable fire = actorExecute.getFirearm().fire();
            if (fire != null) {
                fire.getAnimation().setRotation(Direction.fromAngle(actorExecute.getAnimation().getRotation()).getAngle());
                Objects.requireNonNull(actorExecute.getScene()).addActor(fire, actorExecute.getPosX() + 10,
                    actorExecute.getPosY() + 10);
                new Move<Fireable>(Direction.fromAngle(getActor().getAnimation().getRotation()), Float.MAX_VALUE).scheduleFor(fire);
            }
        }
        setDone(true);
    }
}
