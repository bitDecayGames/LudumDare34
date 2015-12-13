package ludum.dare;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlasLoader;
import ludum.dare.actors.GameObject;
import ludum.dare.screens.RaceScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/12/2015.
 */
public class RacerGame extends Game {
    public static AssetManager assetManager = new AssetManager();
    public static void queueAssetsForLoad() {
        assetManager.setLoader(AnimagicTextureAtlas.class, new AnimagicTextureAtlasLoader(new InternalFileHandleResolver()));
        RacerGame.assetManager.load("packed/test.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("packed/menu.atlas", AnimagicTextureAtlas.class);
    }

    @Override
    public void create() {
        queueAssetsForLoad();
        assetManager.finishLoading();

        // TODO JJCT pass real players to this.
        List<GameObject> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new GameObject());
        }
        setScreen(new RaceScreen(this));
    }
}
