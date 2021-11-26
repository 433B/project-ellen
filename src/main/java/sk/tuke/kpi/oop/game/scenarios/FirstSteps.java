package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

public class FirstSteps implements SceneListener {
    private Energy energy;
    private Ammo ammo;

    private FireExtinguisher fireExtinguisher = new FireExtinguisher();
    private Ripley ripley;
    private Hammer hammer = new Hammer();
    private Mjolnir mjolnir = new Mjolnir();
    private Wrench wrench = new Wrench();

    public FirstSteps() {
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = new Ripley();
        scene.addActor(ripley, 0, 0);
        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);

        energy = new Energy();
        scene.addActor(energy, -100, 50);

        ammo = new Ammo();
        scene.addActor(ammo, -100, 100);

        new When<> (
            () -> ripley.intersects(energy),
            new Invoke<>(() -> energy.useWith(ripley))
        ).scheduleFor(ripley);

        new When<> (
            () -> ripley.intersects(ripley),
            new Invoke<>(() -> ammo.useWith(ripley))
        ).scheduleFor(ripley);

        ripley.getBackpack().add(fireExtinguisher);
        ripley.getBackpack().add(hammer);
        ripley.getBackpack().add(wrench);
        ripley.getBackpack().add(hammer);
        ripley.getBackpack().add(wrench);
        ripley.getBackpack().add(fireExtinguisher);
        ripley.getBackpack().add(hammer);
        scene.addActor(wrench, 50, 55);
        scene.addActor(hammer, 50, 65);
        scene.addActor(fireExtinguisher, 50, 70);
        scene.addActor(mjolnir, 50, 100);

        scene.getGame().pushActorContainer(ripley.getBackpack());
        ripley.getBackpack().shift();

        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        if (ripley != null) {
            scene.follow(ripley);
            ripley.showRipleyState(scene);
        }
    }

}
