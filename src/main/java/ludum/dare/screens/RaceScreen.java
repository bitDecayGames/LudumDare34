package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.actors.GameObject;

import java.util.ArrayList;
import java.util.List;

public class RaceScreen implements Screen {

    OrthographicCamera[] cameras = new OrthographicCamera[4];
    AnimagicSpriteBatch batch;

    List<GameObject> gameObjects = new ArrayList<>();
    List<GameObject> players = new ArrayList<>();


    public RaceScreen(List<GameObject> players) {
        this.players = players;
    }

    @Override
    public void show() {
        for (int i = 0; i < cameras.length; i++) cameras[i] = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / cameras.length);
        batch = new AnimagicSpriteBatch();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) cameras[0].translate(-1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) cameras[0].translate(1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) cameras[0].translate(0, 1);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) cameras[0].translate(0, -1);
        update(delta);
        for (Camera cam : cameras) cam.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for (int i = 0; i < cameras.length; i++){
            Gdx.gl.glViewport(0, Gdx.graphics.getHeight() / cameras.length * i, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / cameras.length );
            OrthographicCamera cam = cameras[i];
            batch.setProjectionMatrix(cam.combined);
            batch.begin();
            draw();
            batch.end();
        }
    }

    public void update(float delta){
        gameObjects.forEach(obj -> obj.update(delta));
    }

    public void draw(){
        gameObjects.forEach(obj -> obj.draw(batch));
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

    }
}
