package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive {
    private Animation playerRipl;
    private Animation dieRipl;

    private Health health;
    private float speed;
    private int ammo;
    private Backpack backpack;
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);

    public Ripley() {
        super("Ellen");
        playerRipl = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(playerRipl);
        this.health = new Health(100, 100);
        this.ammo = 50;
        this.speed = 3;
        backpack = new Backpack("Ripley's backpack", 10);
        playerRipl.stop();
        dieRipl = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
    }

    @Override
    public void startedMoving(Direction direction) {
        if (Direction.NORTH == direction) {
            playerRipl.setRotation(0);
            playerRipl.play();
        } else if (Direction.NORTHWEST == direction) {
            playerRipl.setRotation(45);
            playerRipl.play();
        } else if (Direction.WEST == direction) {
            playerRipl.setRotation(90);
            playerRipl.play();
        } else if (Direction.SOUTHWEST == direction) {
            playerRipl.setRotation(135);
            playerRipl.play();
        } else if (Direction.SOUTH == direction) {
            playerRipl.setRotation(180);
            playerRipl.play();
        } else if (Direction.SOUTHEAST == direction) {
            playerRipl.setRotation(225);
            playerRipl.play();
        } else if (Direction.EAST == direction) {
            playerRipl.setRotation(270);
            playerRipl.play();
        } else if (Direction.NORTHEAST == direction) {
            playerRipl.setRotation(315);
            playerRipl.play();
        }
    }

    private void ellenDied() {
        Scene scene = getScene();

        if (scene != null) {
            setAnimation(dieRipl);
            scene.getMessageBus().publish(RIPLEY_DIED, this);
            scene.cancelActions(this);
        }
    }

    public void showRipleyState(Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("Energy: " + health.getValue(), 120, yTextPos);
        scene.getGame().getOverlay().drawText("Ammo: " + 50, 260, yTextPos);
    }

    public int getAmmo() {
        return this.ammo;
    }

    public void setAmmo(int a) {
        this.ammo = a;
    }

    @Override
    public void stoppedMoving() {
        playerRipl.pause();
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public int getSpeed() {
        return (int) this.speed;
    }

    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.health.onExhaustion(this::ellenDied);
    }
}
