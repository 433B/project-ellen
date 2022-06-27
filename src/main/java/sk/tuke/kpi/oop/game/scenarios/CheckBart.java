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

import java.util.function.Consumer;

public class CheckBart implements SceneListener {
    Wrench wrench = new Wrench();

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if (ripley != null) {
            scene.follow(ripley);
        }

        MovableController movable = new MovableController(ripley);
        scene.getInput().registerListener(movable);

        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);

        ShooterController shooterController = new ShooterController(ripley);
        scene.getInput().registerListener(shooterController);

        if (ripley != null) {
            scene.getGame().pushActorContainer(ripley.getBackpack());
        }
        if (ripley != null) {
            ripley.getBackpack().shift();
        }
        if (ripley != null) {
            ripley.getBackpack().add(wrench);
        }
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        Ripley ellen = scene.getFirstActorByType(Ripley.class);
        assert ellen != null;
        ellen.showRipleyState(scene);
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {
        Consumer<Actor> new_actor = (a) -> System.out.println("Add new actor!");
        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, new_actor);
    }

    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            assert name != null;
            switch (name) {
                case "ellen":
                    return new Ripley();
                case "alien":
                    return new Alien(100, new RandomlyMoving());
                case "door":
                    return new Door();
                case "energy":
                    return new Energy();
                case "alien mother":
                    return new AlienMother(150, new RandomlyMoving());
                case "ammo":
                    return new Ammo();
                case "back door":
                    return new Door("", Door.Orientation.HORIZONTAL);
                case "front door":
                    return new Door("", Door.Orientation.VERTICAL);
                case "ventilator":
                    return new Ventilator();
                case "locker":
                    return new Locker();
                case "access card":
                    return new AccessCard();
            }
            return null;
        }
    }

}
