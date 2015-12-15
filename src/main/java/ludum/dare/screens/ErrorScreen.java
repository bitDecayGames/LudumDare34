package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ludum.dare.RacerGame;

public class ErrorScreen implements Screen {

    RacerGame game;

    private Stage stage;

    public ErrorScreen(RacerGame game, Exception e) {
        this.game = game;

        stage = new Stage();
        TextureAtlas atlas = RacerGame.assetManager.get("skins/ui.atlas", TextureAtlas.class);
        Skin skin = new Skin(Gdx.files.internal("skins/menu-skin.json"), atlas);

        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stackTrace.length; i++) {
            sb.append(stackTrace[i] + "\n");
        }

        Label error = new Label("We are so sorry!  The game crashed :( \nPress SPACE to go back to the main menu.\n\n" + e + "\n\n" + sb.toString(), skin);
        error.setWrap(true);
        error.setFontScale(2);
        error.setColor(Color.WHITE);
        error.setFillParent(true);
        stage.addActor(error);
    }

    @Override
    public void show() {
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

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) game.setScreen(new MainMenuScreen(game));
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
