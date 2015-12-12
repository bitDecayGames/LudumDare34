package ludum.dare;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bytebreakstudios.animagic.texture.AnimagicTexturePacker;
import ludum.dare.screens.RaceScreen;

import java.io.File;

public class Launcher {

    public static void main(String[] arg) {
        AnimagicTexturePacker.pack(new File("sprites"), new File("packed"));
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new RacerGame(), config);
    }
}
