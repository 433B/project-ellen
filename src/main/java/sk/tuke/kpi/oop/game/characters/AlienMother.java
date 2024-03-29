package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;

public class AlienMother
    extends Alien{
    public AlienMother(int health, RandomlyMoving randomlyMoving) {
        super(health, randomlyMoving);

        setAnimation(new Animation("sprites/mother.png",
            112, 162,
            0.2f, Animation.PlayMode.LOOP_REVERSED));
    }
}
