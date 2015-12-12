package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class RaceScreen implements Screen {

    OrthographicCamera[] cameras = new OrthographicCamera[4];
    AnimagicSpriteBatch batch;

    List<TextureRegion> drawShit = new ArrayList<>();

    @Override
    public void show() {
        for (int i = 0; i < cameras.length; i++) cameras[i] = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / cameras.length);
        batch = new AnimagicSpriteBatch();

        drawShit.add(new TextureRegion(new Texture("badlogic.jpg")));
    }

    @Override
    public void render(float v) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) cameras[0].translate(-1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) cameras[0].translate(1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) cameras[0].translate(0, 1);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) cameras[0].translate(0, -1);
        update();
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

    public void update(){

    }

    public void draw(){
        drawShit.forEach(tex -> batch.draw(tex, Gdx.graphics.getWidth() / -2, Gdx.graphics.getHeight() / -2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
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
