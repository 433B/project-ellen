package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Bullet extends AbstractActor implements Movable {
    private Animation bulletAnimation;
    private float speed;

    public Bullet() {
        speed = 4;
        bulletAnimation = new Animation("sprites/bullet.png", 16, 16);
        setAnimation(bulletAnimation);
    }

    @Override
    public void startedMoving(Direction direction) {
        if (Direction.NORTH == direction) {
            bulletAnimation.setRotation(0);
            bulletAnimation.play();
        } else if (Direction.NORTHWEST == direction) {
            bulletAnimation.setRotation(45);
            bulletAnimation.play();
        } else if (Direction.WEST == direction) {
            bulletAnimation.setRotation(90);
            bulletAnimation.play();
        } else if (Direction.SOUTHWEST == direction) {
            bulletAnimation.setRotation(135);
            bulletAnimation.play();
        } else if (Direction.SOUTH == direction) {
            bulletAnimation.setRotation(180);
            bulletAnimation.play();
        } else if (Direction.SOUTHEAST == direction) {
            bulletAnimation.setRotation(225);
            bulletAnimation.play();
        } else if (Direction.EAST == direction) {
            bulletAnimation.setRotation(270);
            bulletAnimation.play();
        } else if (Direction.NORTHEAST == direction) {
            bulletAnimation.setRotation(315);
            bulletAnimation.play();
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

}
