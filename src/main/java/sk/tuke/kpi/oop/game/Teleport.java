package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {

    public Teleport() {
        setAnimation(new Animation("sprites/lift.png", 48, 48, 0.2f, Animation.PlayMode.LOOP_REVERSED));
    }

    public void getDestination() {

    }

    public void setDestination(Teleport destinationTeleport) {

    }

    public void teleportPlayer(Player player) {

    }
}
