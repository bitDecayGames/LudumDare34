package ludum.dare;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlasLoader;
import ludum.dare.screens.SplashScreen;
import ludum.dare.util.SoundLibrary;

public class RacerGame extends Game {

    public static boolean MUSIC_ON = true;
    final static int NUM_PLAYER_ASSETS = 5;

    public static AssetManager assetManager = new AssetManager();
    public static void queueAssetsForLoad() {
        System.out.println("LOADING");
        assetManager.setLoader(AnimagicTextureAtlas.class, new AnimagicTextureAtlasLoader(new InternalFileHandleResolver()));
        RacerGame.assetManager.load("packed/tiles.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("packed/level.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("packed/test.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("packed/power.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("packed/ui.atlas", AnimagicTextureAtlas.class);

        for (int i = 0; i < NUM_PLAYER_ASSETS; i++) {
            RacerGame.assetManager.load("packed/player" + i + ".atlas", AnimagicTextureAtlas.class);
        }

        RacerGame.assetManager.load("packed/upgrades.atlas", AnimagicTextureAtlas.class);
        RacerGame.assetManager.load("skins/ui.atlas", TextureAtlas.class);

        RacerGame.assetManager.load("sfx/cashRegister.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Coin.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/flight.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/highJump.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Jump1.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Jump2.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Jump3.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Jump4.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Powerup.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Punch1.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Punch2.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Punch3.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Punch4.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Select_change.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Select_confirm.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/Slide.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/slotMachine.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/slowdown.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/speed.ogg", Sound.class);
        RacerGame.assetManager.load("sfx/stun.ogg", Sound.class);
    }

    @Override
    public void create() {
        System.out.println("CREATE");
        queueAssetsForLoad();
        assetManager.finishLoading();
        setScreen(new SplashScreen(this));
    }
}
