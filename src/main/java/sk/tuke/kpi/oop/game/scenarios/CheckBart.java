package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.Wrench;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.function.Consumer;

public class CheckBart implements SceneListener {
    Wrench wrench = new Wrench();

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        scene.follow(ripley);

        MovableController movable = new MovableController(ripley);
        scene.getInput().registerListener(movable);

        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);

        ShooterController shooterController = new ShooterController(ripley);
        scene.getInput().registerListener(shooterController);

        scene.getGame().pushActorContainer(ripley.getBackpack());
        ripley.getBackpack().shift();

        ripley.getBackpack().add(wrench);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        Ripley ellen = scene.getFirstActorByType(Ripley.class);
        assert ellen != null;
        ellen.showRipleyState(scene);
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {
        Consumer<Actor> new_actor = (a) -> System.out.println("new actor");
        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, new_actor);
    }

    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            assert name != null;
            if (name.equals("ellen")) {
                return new Ripley();
            }
            if (name.equals("alien")) {
                return new Alien(100, new RandomlyMoving());
            }
            if (name.equals("door")) {
                return new Door();
            }
            if (name.equals("energy")) {
                return new Energy();
            }
            if (name.equals("alien mother")) {
                return new AlienMother(150, new RandomlyMoving());
            }
            if (name.equals("ammo")) {
                return new Ammo();
            }
            if (name.equals("back door")) {
                return new Door("", Door.Orientation.HORIZONTAL);
            }
            if (name.equals("front door")) {
                return new Door("", Door.Orientation.VERTICAL);
            }
            if (name.equals("ventilator")) {
                return new Ventilator();
            }
            if (name.equals("locker")) {
                return new Locker();
            }
            if (name.equals("access card")) {
                return new AccessCard();
            }
            return null;
        }
    }

}
