package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import ludum.dare.RacerGame;

public class LoadingScreen implements Screen {

    RacerGame game;

    private Image ldWallpaper;
    private Image bdWallpaper;
    private Stage stage;

    public LoadingScreen(RacerGame game) {
        this.game = game;

        stage = new Stage();
        ldWallpaper = new Image(new TextureRegion(new Texture(Gdx.files.internal("menu/splash.png"))));
        ldWallpaper.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bdWallpaper = new Image(new TextureRegion(new Texture(Gdx.files.internal("menu/bitDecay.png"))));
        bdWallpaper.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(ldWallpaper);
        stage.addActor(bdWallpaper);
    }

    @Override
    public void show() {
        bdWallpaper.addAction(Actions.alpha(0));
        ldWallpaper.addAction(
                Actions.sequence(
                        Actions.alpha(0),
                        Actions.delay(0.2f),
                        Actions.fadeIn(1f),
                        Actions.run(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        nextScreen();
                                    }
                                }
                        )
                )
        );
    }

    private void nextScreen() {
        game.setScreen(new RaceScreen(game));
    }

    @Override
    public void render(float delta) {
        RacerGame.assetManager.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
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
        dispose();
    }

    @Override
    public void dispose() {
    }
}
