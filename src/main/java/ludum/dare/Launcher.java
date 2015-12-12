package ludum.dare;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ludum.dare.screens.RaceScreen;

public class Launcher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new Game() {
            @Override
            public void create() {
                this.setScreen(new RaceScreen());
            }
        }, config);
    }
}
