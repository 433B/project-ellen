package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;

public class EscapeRoom implements SceneListener {

    private Ripley ripley = new Ripley();
    private Alien alien = new Alien();

    @Override
    public void sceneCreated(@NotNull Scene scene) {
//        World.ACTOR_ADDED_TOPIC();
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = scene.getFirstActorByType(Ripley.class);
        assert ripley != null;
        scene.follow(ripley);

        Door door = new Door();
        scene.addActor(door, 200, 250);

        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        Ripley ripley = new Ripley();
        ripley.showRipleyState(scene);
    }

    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            assert name != null;
            if (name.equals("ellen")) {
                return new Ripley();
            }
            if (name.equals("alien")) {
                return new Alien();
            }
            if (name.equals("energy")) {
                return new Energy();
            }
            if (name.equals("alien mother")) {
                return new AlienMother();
            }
            if (name.equals("ammo")) {
                return new Ammo();
            }
            return null;
        }
    }

}
