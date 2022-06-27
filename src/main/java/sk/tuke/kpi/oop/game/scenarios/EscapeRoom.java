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
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.function.Consumer;

public class EscapeRoom implements SceneListener {
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        assert ripley != null;
        scene.follow(ripley);

        Disposable move = scene.getInput().registerListener(new MovableController(ripley));
        Disposable keeper = scene.getInput().registerListener(new KeeperController(ripley));
        Disposable shooter = scene.getInput().registerListener(new ShooterController(ripley));

        LockedDoor lockedDoor = new LockedDoor();
        scene.addActor(lockedDoor, 300, 100);

        Door door = new Door("D", Door.Orientation.HORIZONTAL);
        scene.addActor(door, 150, 150);

        AccessCard accessCard = new AccessCard();
        ripley.getBackpack().add(accessCard);

        writeMessage(scene, ripley, move, keeper, shooter);
    }

    private void writeMessage(@NotNull Scene scene, Ripley ripley, Disposable move, Disposable keeper, Disposable shooter) {
        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ripley.decreaseEnergy());

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> move.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> keeper.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> shooter.dispose());

        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED,
            (Ripley) -> ripley.stopDecreasingEnergy().dispose());
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        Ripley ellen = scene.getFirstActorByType(Ripley.class);
        assert ellen != null;
        ellen.showRipleyState(scene);
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {
        Consumer<Actor> new_actor = (a) -> System.out.println("Add new actor");
        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, new_actor);
    }

    public static class Factory implements ActorFactory {
        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if (name != null) {
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
                    case "ventilator":
                        return new Ventilator();
                    case "locked":
                        return new Locker();
                    default:
                        break;
                }
            }
            return null;
        }
    }
}
