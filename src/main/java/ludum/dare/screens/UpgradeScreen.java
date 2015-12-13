package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.actors.GameObject;
import ludum.dare.actors.player.Player;
import ludum.dare.components.upgradeComponents.*;
import ludum.dare.interfaces.IComponent;
import ludum.dare.shop.UpgradeGroup;
import ludum.dare.shop.UpgradeOption;

import java.util.ArrayList;
import java.util.List;

public class UpgradeScreen implements Screen {

    private static ArrayList<Class<? extends IComponent>> MASTER_LIST = new ArrayList<>();
    static {
        MASTER_LIST.add(DoubleJumpComponent.class);
        MASTER_LIST.add(EmptyUpgradeComponent.class);
        MASTER_LIST.add(JetPackComponent.class);
        MASTER_LIST.add(MetalComponent.class);
        MASTER_LIST.add(MysteryBagComponent.class);
        MASTER_LIST.add(SpeedComponent.class);
        MASTER_LIST.add(WallJumpComponent.class);
    }

    OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    AnimagicSpriteBatch batch;

    List<UpgradeGroup> groups = new ArrayList<>();

    private int spacePerGroup;

    @Override
    public void show() {
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        camera.update();
        batch = new AnimagicSpriteBatch(camera);
        batch.isShaderOn(false);

        // need a way to get the players in the game.
        List<GameObject> players = new ArrayList<>();
        players.add(new GameObject());
        players.add(new GameObject());
        players.add(new GameObject());
        players.add(new GameObject());

        spacePerGroup = Gdx.graphics.getHeight() / players.size();
        for (GameObject player : players) {
            UpgradeGroup upgradeGroup = buildPlayerOptions(player);
            groups.add(upgradeGroup);
        }
    }

    private UpgradeGroup buildPlayerOptions(Player player) {
        UpgradeGroup group = new UpgradeGroup();
        group.initialize(player);
        int tries = 10;
        while (tries > 0) {
            tries--;
            Class<? extends IComponent> upgradeComponent = MASTER_LIST.get((int) (Math.random() * MASTER_LIST.size()));
            if (!player.hasComponent(upgradeComponent)) {
                group.addChoice(new UpgradeOption(upgradeComponent, ));
            }
        }
        return group;
    }

    @Override
    public void render(float delta) {
        for (UpgradeGroup group : groups) {
            group.update(delta);
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setAmbientColor(Color.WHITE);
        batch.setAmbientIntensity(0.01f);
        batch.setNextLight(0, 0, 0.1f, 0.9f, Color.RED);

        for (int i = 0; i < groups.size(); i++) {
            UpgradeGroup group = groups.get(i);
            int yTop = (groups.size() - i) * spacePerGroup;
            int yBottom = yTop - spacePerGroup;
            group.render(batch, yTop, yBottom);
        }
        batch.end();
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

    }

    @Override
    public void dispose() {

    }
}
