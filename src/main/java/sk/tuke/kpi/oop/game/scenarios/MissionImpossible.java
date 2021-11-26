package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.Objects;


public class MissionImpossible implements SceneListener {

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        assert ripley != null;
        scene.follow(ripley);

        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);

//        if (ripley.getEnergy() == 0) {
        scene.getInput().registerListener(movableController).dispose();
        scene.getInput().registerListener(keeperController).dispose();
//        }

        scene.getGame().pushActorContainer(ripley.getBackpack());
        ripley.getBackpack().shift();

        ripley.getBackpack().add(new AccessCard());
        ripley.getBackpack().add(new Hammer());

//        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ripley.getBackpack());

//        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) -> ripley.getEnergy());
//        scene.getMessageBus().subscribe(Door.DOOR_CLOSED, );
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        Ripley ripley = new Ripley();
        ripley.showRipleyState(scene);
    }

    public static class Factory implements ActorFactory {
        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            switch (Objects.requireNonNull(name)) {
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "door":
                    return new LockedDoor();
                case "access card":
                    return new AccessCard();
                case "locker":
                    return new Locker();
                case "ventilator":
                    return new Ventilator();
                default:
                    return null;
            }
        }
    }
}
