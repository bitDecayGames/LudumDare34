package ludum.dare;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlasLoader;
import ludum.dare.screens.SplashScreen;

public class RacerGame extends Game {

    public static boolean MUSIC_ON = false;
    final static int NUM_PLAYER_ASSETS = 5;

    public static AssetManager assetManager = new AssetManager();
    public static void queueAssetsForLoad() {
        assetManager.setLoader(AnimagicTextureAtlas.class, new AnimagicTextureAtlasLoader(new InternalFileHandleResolver()));
        RacerGame.assetManager.load("packed/tiles.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("packed/level.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("packed/test.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("packed/ui.atlas", AnimagicTextureAtlas.class);
        for (int i = 0; i < NUM_PLAYER_ASSETS; i++) {
            RacerGame.assetManager.load("packed/player" + i + ".atlas", AnimagicTextureAtlas.class);
        }
        RacerGame.assetManager.load("packed/upgrades.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("skins/ui.atlas", TextureAtlas.class);
    }

    @Override
    public void create() {
        queueAssetsForLoad();
        assetManager.finishLoading();
        setScreen(new SplashScreen(this));
    }
}
