package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.level.LevelObject;
import ludum.dare.RacerGame;
import ludum.dare.levelobject.AINodeLevelObject;
import ludum.dare.levels.LevelSegmentAggregator;
import ludum.dare.levels.LevelSegmentGenerator;
import ludum.dare.util.SoundLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoadingScreen implements Screen {

    Music music;

    RacerGame game;

    private Image ldWallpaper;
    private Stage stage;

    private LevelLoader loader;

    public LoadingScreen(RacerGame game) {
        this.game = game;

        stage = new Stage();
        ldWallpaper = new Image(new TextureRegion(new Texture(Gdx.files.internal("menu/loading.png"))));
        ldWallpaper.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(ldWallpaper);
    }

    @Override
    public void show() {
        if(RacerGame.MUSIC_ON) {
            music = SoundLibrary.loopMusic("a_journey_awaits");
        }

        ldWallpaper.addAction(
                Actions.sequence(
                        Actions.alpha(0),
                        Actions.delay(0.2f),
                        Actions.fadeIn(1f)
                )
        );

        loader = new LevelLoader();
        new Thread(loader).start();
    }

    private void nextScreen() {
        game.setScreen(new RaceScreen(game, loader));
    }

    @Override
    public void render(float delta) {
        RacerGame.assetManager.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        if (loader != null && loader.done.get()) {
            if (music.isPlaying()) {
                music.stop();
            }
            game.setScreen(new RaceScreen(game, loader));
            loader = null;
        }
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

    public static class LevelLoader implements Runnable {
        AtomicBoolean done = new AtomicBoolean(false);
        Level level = new Level(32);
        List<AINodeLevelObject> aiNodes = new ArrayList<>();

        @Override
        public void run() {
            generateNextLevel(10);
        }

        public void generateNextLevel(int length) {
            LevelSegmentGenerator generator = new LevelSegmentGenerator(length);
            List<Level> levels = generator.generateLevelSegments();

            for (Level level : levels) {
                List<AINodeLevelObject> lvlNodes = new ArrayList<>();
                for (LevelObject otherObject : level.otherObjects) {
                    if (otherObject instanceof AINodeLevelObject) {
                        lvlNodes.add((AINodeLevelObject) otherObject);
                    }
                }
                lvlNodes.sort((a, b) -> a.nodeIndex - b.nodeIndex);
                aiNodes.addAll(lvlNodes);
            }

            level = LevelSegmentAggregator.assembleSegments(levels);
            done.set(true);
        }
    }
}
