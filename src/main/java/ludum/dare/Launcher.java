package ludum.dare;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bytebreakstudios.animagic.texture.AnimagicTexturePacker;

import java.io.File;

public class Launcher {

    public static final String RENDER_SMALL = "small";
    public static final String RENDER_MED = "medium";
    public static final String RENDER_BIG = "big";

    public static void main(String[] arg) {
        String windowSize = RENDER_MED;
        if (arg.length >= 1) {
            if (arg[0].equals(RENDER_SMALL) || arg[0].equals(RENDER_MED) || arg[0].equals(RENDER_BIG)) {
                windowSize = arg[0];
            }
        }
        if (arg != null && arg.length > 3) {
            AnimagicTexturePacker.pack(new File("sprites"), new File("packed"));
            AnimagicTexturePacker.pack(new File("assets"), new File("packed"));
        }
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Dendrite";
        config.resizable = false;
        if (RENDER_SMALL.equals(windowSize)) {
            config.width = 800;
            config.height = 450;
        } else if (RENDER_MED.equals(windowSize)) {
            config.width = 1280;
            config.height = 720;
        } else {
            config.width = 1600;
            config.height = 900;
        }
        config.fullscreen = true;
            new LwjglApplication(new RacerGame(windowSize), config);
    }
}
