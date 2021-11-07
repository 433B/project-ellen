package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    private boolean isOn;

    public Helicopter() {
        setAnimation(new Animation("sprites/heli.png", 64, 64, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public void startSearchAndDestroy() {
        this.isOn = true;
    }

    public void searchAndDestroy(Player player) {
        if (isOn && player != null) {
            if (player.getPosX() == this.getPosX() && player.getPosY() == this.getPosY()) {
                player.setEnergy(player.getEnergy() - 1);
            }

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


//    @Override
//    public void addedToScene(@NotNull Scene scene) {
//        super.addedToScene(scene);
//        if (player != null) {
//            new Invoke<>(this::searchAndDestroy).scheduleFor(player);
//        }
//    }
}

