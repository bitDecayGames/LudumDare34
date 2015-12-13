package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import ludum.dare.RacerGame;
import ludum.dare.control.InputAction;
import ludum.dare.control.InputUtil;
import ludum.dare.control.Xbox360Pad;


/**
 * Created by mwingfield on 8/6/15.
 */
public class MainMenuScreen implements Screen {

    private RacerGame game;
    private Stage stage = new Stage();

    private Table menu;
    private Image background;
    private Label title;
    private TextButton playBtn;
    private TextButton creditsBtn;
    private TextButton quitBtn;

    private Music music;

    boolean active = true;

    public MainMenuScreen(final RacerGame game) {
        this.game = game;

        TextureAtlas atlas = RacerGame.assetManager.get("skins/ui.atlas", TextureAtlas.class);
        Skin skin = new Skin(Gdx.files.internal("skins/menu-skin.json"), atlas);

        background = new Image(new TextureRegion(new Texture(Gdx.files.internal("menu/splash.png"))));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        title = new Label("Scabs", skin);
        title.setFontScale(2);
        title.setAlignment(Align.top);
        title.setFillParent(true);

        playBtn = new TextButton("Play", skin, "button");
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                AtlasManager.instance.finishLoading();

                active = false;
            }
        });
        creditsBtn = new TextButton("Credits", skin, "button");
        creditsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int seconds = 1;
                int moveOff = Gdx.graphics.getWidth();
                menu.addAction(Actions.sequence(
                        Actions.moveBy(moveOff, 0, seconds),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("CREDITS BUTTON PUSHED");
//                                game.setScreen(new CreditsScreen(game));
                            }
                        })
                ));
            }
        });
        quitBtn = new TextButton("Exit", skin, "button");
        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        menu = new Table();
        menu.add(playBtn).height(60).padBottom(20).padTop(150).row();
        menu.add(creditsBtn).height(60).padBottom(20).row();
        menu.add(quitBtn).height(60).padBottom(20).row();
        menu.align(Align.center);
        menu.setFillParent(true);

        stage.addActor(background);
        stage.addActor(title);
        stage.addActor(menu);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
//         animate the main menu when entering
        int seconds = 1;
        int moveOff = Gdx.graphics.getWidth();
        menu.addAction(Actions.alpha(0));
        background.addAction(Actions.sequence(
                Actions.alpha(0),
                Actions.delay(0.25f),
                Actions.fadeIn(2.5f)

        ));
        menu.addAction(Actions.sequence(
                Actions.fadeIn(3.5f)
        ));
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        if (!active) {
            stage.addAction(Actions.sequence(
                    Actions.fadeOut(1),
                    Actions.delay(.5f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new SetupScreen(game));
                        }
                    })
            ));
        }
    }


    public void update(float delta){
        if(InputUtil.checkInputs(Input.Keys.SPACE, Xbox360Pad.START)){
            game.setScreen(new SetupScreen(game));
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
        stage.dispose();
    }
}
