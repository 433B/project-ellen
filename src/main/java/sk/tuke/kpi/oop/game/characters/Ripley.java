package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;


public class Ripley
    extends AbstractActor implements Movable, Keeper, Alive, Armed {
    private final Animation player;

    public static final Topic<Ripley> RIPLEY_DIED =
        Topic.create("Ripley is died! Try again", Ripley.class);

    private final float ripleySpeed;
    private int ammo;
    private final Backpack ripleyBackpack;
    private Disposable disposable;
    private final Health ripleyHealth;
    private Firearm ripleyGun;

    public Ripley() {
        super("Ellen");
        ripleySpeed = 2;
        ammo = 100;
        disposable = null;

        ripleyGun = new Gun(100, 150);
        ripleyHealth = new Health(100, 100);
        ripleyBackpack = new Backpack("ripley's backpack", 10);

        player = new Animation("sprites/player.png",
            32, 32,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG);

        setAnimation(player);
        player.stop();

        ripleyHealth.onExhaustion(() -> {
            setAnimation(new Animation("sprites/player_die.png",
                32, 32,
                0.1f, Animation.PlayMode.ONCE));
            getScene().getMessageBus().publish(RIPLEY_DIED, this);
        });
    }

    @Override
    public void startedMoving(Direction direction) {
        switch (direction) {
            case NORTH:
                player.setRotation(0);
                break;
            case NORTHWEST:
                player.setRotation(45);
                break;
            case WEST:
                player.setRotation(90);
                break;
            case SOUTHWEST:
                player.setRotation(135);
                break;
            case SOUTH:
                player.setRotation(180);
                break;
            case SOUTHEAST:
                player.setRotation(225);
                break;
            case EAST:
                player.setRotation(270);
                break;
            case NORTHEAST:
                player.setRotation(315);
                break;
        }
        player.play();
    }

    public void showRipleyState(Scene scene) {
        int windowHeight = getScene()
            .getGame()
            .getWindowSetup()
            .getHeight();

        int yTextPos =
            windowHeight - GameApplication.STATUS_LINE_OFFSET;

        scene.getGame()
            .getOverlay()
            .drawText("Energy " + ripleyHealth.getValue(),
                120,
                yTextPos);

        scene.getGame()
            .getOverlay()
            .drawText("Ammo " + getFirearm().getAmmo(),
                240,
                yTextPos);
    }

    public void decreaseEnergy() {
        if (ripleyHealth.getValue() != 0 && ripleyHealth.getValue() >= 0) {
            diedHero();
        } else {
            setAnimation(new Animation("sprites/player_die.png",
                32, 32,
                0.1f, Animation.PlayMode.ONCE));
            getScene().getMessageBus().publish(RIPLEY_DIED, this);
        }
    }

    public void diedHero() {
        disposable = new Loop<>(
            new ActionSequence<>(
                new Invoke<>(() -> {
                    if (ripleyHealth.getValue() == 0) {
                        setAnimation(new Animation("sprites/player_die.png",
                            32, 32,
                            0.1f, Animation.PlayMode.ONCE));
                        getScene().getMessageBus().publish(RIPLEY_DIED, this);
                    }
                    else getHealth().drain(5);
                }),
                new Wait<>(1)
            )
        ).scheduleFor(this);
    }

    public Disposable stopDecreasingEnergy() {
        return disposable;
    }

    @Override
    public void stoppedMoving() {
        player.stop();
    }

    @Override
    public Health getHealth() {
        return this.ripleyHealth;
    }

    @Override
    public Firearm getFirearm() {
        return this.ripleyGun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.ripleyGun = weapon;
    }

    @Override
    public Backpack getBackpack() {
        return this.ripleyBackpack;
    }

    @Override
    public int getSpeed() {
        return (int) this.ripleySpeed;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int a) {
        this.ammo = a;
    }
}
