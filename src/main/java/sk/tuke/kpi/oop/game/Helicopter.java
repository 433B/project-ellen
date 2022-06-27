package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class Helicopter extends AbstractActor {
    private Player player;

    public Helicopter() {
        setAnimation(new Animation("sprites/heli.png",
            64, 64,
            0.1f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public void searchAndDestroy() {

        player = getScene().getFirstActorByType((Player.class));
        new Loop<>(new Invoke<>(this::search)).scheduleFor(this);
    }

    public void search() {
        if (player != null) {
            if (player.getPosX() == this.getPosX() && player.getPosY() == this.getPosY())
                player.setEnergy(player.getEnergy() - 1);

            helicopterDamage();
        }
    }

    private void helicopterDamage() {
        if (player.getPosY() > this.getPosY()) {
            this.setPosition(this.getPosX(), this.getPosY() + 1);
        } else if (player.getPosY() < this.getPosY()) {
            this.setPosition(this.getPosX(), this.getPosY() - 1);
        }

        if (player.getPosX() > this.getPosX()) {
            this.setPosition(this.getPosX() + 1, this.getPosY());
        } else if (player.getPosX() < this.getPosX()) {
            this.setPosition(this.getPosX() - 1, this.getPosY());
        }
    }
}

