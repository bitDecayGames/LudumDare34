package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.RacerGame;
import ludum.dare.actors.GameObject;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;
import ludum.dare.control.ControllerScreenObject;
import ludum.dare.control.InputUtil;
import ludum.dare.control.Xbox360Pad;

import java.util.ArrayList;
import java.util.List;

public class SetupScreen implements Screen {

    OrthographicCamera camera;
    AnimagicSpriteBatch batch;

    RacerGame game;

    List<ControllerScreenObject> inputObjects;
    List<GameObject> players;

    final static int NUM_PLAYERS = 4;

    // Keyboard keys for selections.
    final static List<Integer> keyboardSelectKeys = new ArrayList<>();
    static {
        keyboardSelectKeys.add(Input.Keys.Q);
        keyboardSelectKeys.add(Input.Keys.W);
        keyboardSelectKeys.add(Input.Keys.E);
        keyboardSelectKeys.add(Input.Keys.R);
    }

    final static List<Integer> keyboardDeselectKeys = new ArrayList<>();
    static {
        keyboardDeselectKeys.add(Input.Keys.A);
        keyboardDeselectKeys.add(Input.Keys.S);
        keyboardDeselectKeys.add(Input.Keys.D);
        keyboardDeselectKeys.add(Input.Keys.F);
    }

    // Onscreen positions
    final static float BOX_SIDE = 250;
    final static float X = 200;
    final static float Y = 325;
    final static List<PositionComponent> playerControllerPositions = new ArrayList<>();
    static {
        playerControllerPositions.add(new PositionComponent(-X -(BOX_SIDE / 2), Y - BOX_SIDE));
        playerControllerPositions.add(new PositionComponent(X - (BOX_SIDE / 2), Y - BOX_SIDE));
        playerControllerPositions.add(new PositionComponent(-X - (BOX_SIDE / 2), -Y));
        playerControllerPositions.add(new PositionComponent(X - (BOX_SIDE / 2), -Y));
    }

    public SetupScreen(RacerGame game) {
        if (game == null) {
            throw new Error("game cannot be null");
        }

        this.game = game;

        players = new ArrayList<>();
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players.add(new GameObject());
        }
    }

    public List<GameObject> getResults() {
        // Copy inputs into players.
        for (int i = 0; i < inputObjects.size(); i++) {
            players.get(i).append(inputObjects.get(i).getInputComponent());
        }

        return players;
    }

    private void setupPlayers() {
        SizeComponent size = new SizeComponent(BOX_SIDE, BOX_SIDE);
        for (int i = 0; i < players.size(); i++) {
            ControllerScreenObject obj = new ControllerScreenObject(keyboardSelectKeys.get(i), keyboardDeselectKeys.get(i), i, playerControllerPositions.get(i), size);
            inputObjects.add(obj);
        }
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.lookAt(0, 0, 0);
        batch = new AnimagicSpriteBatch(camera);
        batch.isShaderOn(false);

        inputObjects = new ArrayList<>();

        setupPlayers();
    }

    @Override
    public void render(float v) {
        inputObjects.forEach(obj -> obj.update(v));

        if (InputUtil.checkInputs(Input.Keys.ENTER, Xbox360Pad.START)) {
            // Start race.
            game.setScreen(new RaceScreen(getResults()));
        }

        camera.update();
        Vector3 mousePos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        Gdx.gl.glClearColor(100f / 255f, 139f / 255f, 237f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.setAmbientColor(Color.WHITE);
        batch.setAmbientIntensity(0.01f);
        batch.setNextLight(mousePos.x, mousePos.y, 0.1f, 0.9f, Color.WHITE);
        inputObjects.forEach(obj -> obj.draw(batch));
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

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
        camera = null;

        batch.dispose();
        batch = null;

        inputObjects.clear();
        inputObjects = null;
    }
}
