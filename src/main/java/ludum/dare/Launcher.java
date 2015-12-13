package ludum.dare;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bytebreakstudios.animagic.texture.AnimagicTexturePacker;

import java.io.File;

public class Launcher {

    public static void main(String[] arg) {
        AnimagicTexturePacker.pack(new File("sprites"), new File("packed"));
        AnimagicTexturePacker.pack(new File("assets"), new File("packed"));
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1600;
        config.height = 900;
        new LwjglApplication(new RacerGame(), config);
    }
}
