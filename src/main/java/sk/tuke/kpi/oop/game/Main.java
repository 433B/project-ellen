package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.oop.game.scenarios.CheckBart;

public class Main {
    public static void main(String[] args) {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 700, 500);
        Game game = new GameApplication(windowSetup, new LwjglBackend());
        Scene bartRoom = new World("check-bart", "maps/check-bart.tmx", new CheckBart.Factory());
        CheckBart bart = new CheckBart();
        bartRoom.addListener(bart);
        game.addScene(bartRoom);
        game.start();
        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);
    }
}
