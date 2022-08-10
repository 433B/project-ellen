package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
public class Monster
    extends Alien {
    public Monster(int health, RandomlyMoving randomlyMoving) {
        super(health, randomlyMoving);

        setAnimation(new Animation("sprites/monster.png",
            216, 128,
            0.2f, Animation.PlayMode.LOOP_REVERSED));
    }
}
