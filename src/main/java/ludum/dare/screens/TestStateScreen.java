package ludum.dare.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import ludum.dare.RacerGame;
import ludum.dare.actors.player.Player;

/**
 * THIS CLASS IS FOR DEBUGGING THE PLAYER, SHOULD NOT BE USED!!!!!!!!!
 */
public class TestStateScreen implements Screen {

    OrthographicCamera camera;
    AnimagicSpriteBatch batch;

    Player player;

    public TestStateScreen(Game game) {
        super();
        RacerGame.assetManager.load("packed/player.atlas", AnimagicTextureAtlas.class);
        RacerGame.queueAssetsForLoad();
    }

    @Override
    public void show() {
        RacerGame.assetManager.finishLoading();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new AnimagicSpriteBatch(camera);
        batch.isShaderOn(true);

        player = new Player(0);
    }

    @Override
    public void render(float delta) {
        player.update(delta);

        camera.update();

        Gdx.gl.glClearColor(0, 0.1f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setAmbientColor(Color.WHITE);
        batch.setAmbientIntensity(0.01f);
        batch.setNextLight(0, 0, 0.1f, 0.9f, Color.WHITE);

        player.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
