package ludum.dare;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bitdecay.jump.leveleditor.render.LevelEditor;
import com.bytebreakstudios.animagic.texture.AnimagicTexturePacker;
import ludum.dare.actors.player.Player;
import ludum.dare.components.KeyboardControlComponent;
import ludum.dare.screens.RaceScreen;
import ludum.dare.util.Players;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/13/2015.
 */
public class EditorLauncher {
    public static void main(String[] arg) {
        AnimagicTexturePacker.pack(new File("sprites"), new File("packed"));
        AnimagicTexturePacker.pack(new File("assets"), new File("packed"));
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1600;
        config.height = 900;
        new LwjglApplication(new Game() {
            @Override
            public void create() {
                LevelEditor.TILE_SIZE = 32;
                LevelEditor.setAssetsFolder("../../../../Jump/jump-leveleditor/assets");
                RacerGame game = new RacerGame();
                RacerGame.queueAssetsForLoad();
                RacerGame.assetManager.finishLoading();

                List<Player> players = new ArrayList<>();
                Player playerInstance = new Player();
                playerInstance.append(new KeyboardControlComponent());
                players.add(playerInstance);
                Players.initialize(players);

                RaceScreen raceScreen = new RaceScreen(game);
                raceScreen.show();
                setScreen(new LevelEditor(raceScreen));
            }
        }, config);
    }
}
