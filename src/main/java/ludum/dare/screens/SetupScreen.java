package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.actors.GameObject;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;
import ludum.dare.control.ControllerScreenObject;

import java.util.ArrayList;
import java.util.List;

public class SetupScreen implements Screen {

    OrthographicCamera camera;
    AnimagicSpriteBatch batch;

    List<ControllerScreenObject> inputObjects;

    List<GameObject> players;

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
    final static float X = 200;
    final static float Y = 150;
    final static List<PositionComponent> playerControllerPositions = new ArrayList<>();
    static {
        playerControllerPositions.add(new PositionComponent(-X, Y));
        playerControllerPositions.add(new PositionComponent(X, Y));
        playerControllerPositions.add(new PositionComponent(-X, -Y));
        playerControllerPositions.add(new PositionComponent(X, -Y));
    }

    public SetupScreen(List<GameObject> players) {
        if (players == null) {
            throw new Error("null players not accepted as input.");
        }

        this.players = players;
    }

    public List<GameObject> getPlayers() {
        return players;
    }

    private void setupPlayers() {
        SizeComponent size = new SizeComponent(200, 200);
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

        camera.update();
        Vector3 mousePos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        Gdx.gl.glClearColor(100f / 255f, 139f / 255f, 237f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.setAmbientColor(Color.WHITE);
        batch.setAmbientIntensity(0.01f);
        batch.setNextLight(mousePos.x, mousePos.y, 0.1f, 0.9f, Color.RED);
        batch.setNextLight(-mousePos.x, -mousePos.y, 0.5f, 1, Color.GREEN);
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
