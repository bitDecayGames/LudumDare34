package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.actors.GameObject;
import ludum.dare.components.AnimationComponent;
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

    public SetupScreen(List<GameObject> players) {
        this.players = players;
    }

    public List<ControllerScreenObject> getScreenObjects() {
         return inputObjects;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new AnimagicSpriteBatch(camera);
        inputObjects = new ArrayList<>();

        PositionComponent position = new PositionComponent(0, 0);
        ControllerScreenObject controller = new ControllerScreenObject(Input.Keys.Q, Input.Keys.A, 0, position);

        inputObjects.add(controller);

        // Press a key or button to join
    }

    @Override
    public void render(float v) {
        Array<Controller> activeControllers = Controllers.getControllers();

        getScreenObjects().forEach(obj -> obj.update(v));

        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setLight(0, 0 ,0, 0, 0.5f, Color.BLUE);
        getScreenObjects().forEach(obj -> obj.draw(batch));
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
