package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;

import java.util.Objects;

public class Bullet extends AbstractActor implements Fireable {
    private final Animation bulletAnimation;
    private final float speed;
    private final int gunDamage;

    public Bullet() {
        this.speed = 4;
        this.gunDamage = 30;
        bulletAnimation = new Animation("sprites/bullet.png", 16, 16);
        setAnimation(bulletAnimation);
    }

    @Override
    public void startedMoving(Direction direction) {
        switch (direction) {
            case NORTH:
                bulletAnimation.setRotation(0);
                bulletAnimation.play();
                break;
            case NORTHWEST:
                bulletAnimation.setRotation(45);
                bulletAnimation.play();
                break;
            case WEST:
                bulletAnimation.setRotation(90);
                bulletAnimation.play();
                break;
            case SOUTHWEST:
                bulletAnimation.setRotation(135);
                bulletAnimation.play();
                break;
            case SOUTH:
                bulletAnimation.setRotation(180);
                bulletAnimation.play();
                break;
            case SOUTHEAST:
                bulletAnimation.setRotation(225);
                bulletAnimation.play();
                break;
            case EAST:
                bulletAnimation.setRotation(270);
                bulletAnimation.play();
                break;
            case NORTHEAST:
                bulletAnimation.setRotation(315);
                bulletAnimation.play();
                break;
            default:
                break;
        }
    }

    @Override
    public void collidedWithWall() {
        getScene().removeActor(this);
    }

    @Override
    public int getSpeed() {
        return (int) this.speed;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new Loop<>(
            new Invoke<>(this::shooting)
        ).scheduleFor(this);
    }

    private void shooting() {
        for (Actor actor : getScene().getActors()) {
            if (this.intersects(actor) && (actor instanceof Alive)) {
                ((Alive) actor).getHealth().drain(gunDamage);
                collidedWithWall();
            }
        }
    }
}
