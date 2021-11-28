package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
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

        Door door = new Door();
        scene.addActor(door, 150, 150);

        AccessCard accessCard = new AccessCard();
        ripley.getBackpack().add(accessCard);

        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ripley.decreaseEnergy());

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> move.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> keeper.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> shooter.dispose());

        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) -> ripley.stopDecreasingEnergy().dispose());
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        Ripley ellen = scene.getFirstActorByType(Ripley.class);
        assert ellen != null;
        ellen.showRipleyState(scene);
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {

    }

    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            assert name != null;
            if (name.equals("ellen")) {
                return new Ripley();
            }
//            if (name.equals("alien")) {
//                return new Alien(150, new RandomlyMoving());
//            }
            if (name.equals("energy")) {
                return new Energy();
            }
//            if (name.equals("alien mother")) {
//                return new AlienMother(150, new RandomlyMoving());
//            }
            if (name.equals("ammo")) {
                return new Ammo();
            }
            return null;
        }
    }
}
