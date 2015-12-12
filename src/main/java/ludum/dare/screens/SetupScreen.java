package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import com.bytebreakstudios.animagic.texture.data.AnimagicTextureData;
import ludum.dare.actors.GameObject;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;
import ludum.dare.components.TextureRegionComponent;

import java.util.ArrayList;
import java.util.List;

public class SetupScreen implements Screen {

    OrthographicCamera camera;
    AnimagicSpriteBatch batch;

    List<GameObject> inputObjects;

    List<GameObject> players;

    public SetupScreen(List<GameObject> players) {
        this.players = players;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.lookAt(0, 0, 0);
        batch = new AnimagicSpriteBatch(camera);
        inputObjects = new ArrayList<>();

        PositionComponent p = new PositionComponent(0, 0);
        SizeComponent s = new SizeComponent(400, 400);

        inputObjects.add(new GameObject(p, s, new TextureRegionComponent(new AnimagicTextureRegion(new Texture("bum.png"), new Texture("bum_n.png"), new AnimagicTextureData(200, 200)), p, s)));

        // Press a key or button to join
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
