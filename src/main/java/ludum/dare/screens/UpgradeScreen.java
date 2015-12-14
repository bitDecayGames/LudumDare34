package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.RacerGame;
import ludum.dare.actors.player.Player;
import ludum.dare.components.upgradeComponents.*;
import ludum.dare.interfaces.IProjectile;
import ludum.dare.shop.UpgradeGroup;
import ludum.dare.shop.UpgradeOption;
import ludum.dare.util.Players;
import ludum.dare.util.SoundLibrary;

import java.util.ArrayList;
import java.util.List;

public class UpgradeScreen implements Screen {

    Music music;

    private static ArrayList<UpgradeOption> MASTER_LIST = new ArrayList<>();

    OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    AnimagicSpriteBatch batch;

    List<UpgradeGroup> groups = new ArrayList<>();

    private int spacePerGroup;
    private RacerGame game;

    public UpgradeScreen(RacerGame game) {
        this.game = game;
        if (MASTER_LIST.size() == 0) {
            MASTER_LIST.add(new UpgradeOption(DoubleJumpComponent.class, "doubleJump"));
            MASTER_LIST.add(new UpgradeOption(FloatUpgradeComponent.class, "float"));
            MASTER_LIST.add(new UpgradeOption(MetalComponent.class, "metal"));
            MASTER_LIST.add(new UpgradeOption(MysteryBagComponent.class, "mystery"));
            MASTER_LIST.add(new UpgradeOption(SpeedComponent.class, "speed"));
            MASTER_LIST.add(new UpgradeOption(WallJumpComponent.class, "wallJump"));
            MASTER_LIST.add(new UpgradeOption(FireProjectileComponent.class, "fire"));
            MASTER_LIST.add(new UpgradeOption(PoisonProjectileComponent.class, "poison"));
            MASTER_LIST.add(new UpgradeOption(IceProjectileComponent.class, "ice"));
            MASTER_LIST.add(new UpgradeOption(WebProjectileComponent.class, "web"));
            MASTER_LIST.add(new UpgradeOption(EmptyUpgradeComponent.class, "poop"));
        }
    }

    @Override
    public void show() {

        music = SoundLibrary.GetMusic("a_journey_awaits");

        if(RacerGame.MUSIC_ON) {
            music.play();
        }

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        batch = new AnimagicSpriteBatch(camera);
        batch.isShaderOn(true);

        spacePerGroup = Gdx.graphics.getHeight() / Players.list().size();
        for (Player player : Players.list()) {
            UpgradeGroup upgradeGroup = buildPlayerOptions(player);
            groups.add(upgradeGroup);
        }
    }

    private UpgradeGroup buildPlayerOptions(Player player) {
        UpgradeGroup group = new UpgradeGroup();
        group.initialize(player);
        int tries = 10;
        int added = 0;
outer:  while (tries > 0) {
            tries--;
            UpgradeOption option = MASTER_LIST.get((int) (Math.random() * MASTER_LIST.size()));
            for(UpgradeOption selectedOption : group.getChoices()) {
                if (option == selectedOption) {
                    continue outer;
                }
            }

            Class checkClass = option.clazz;

            if(option.clazz.isAssignableFrom(IProjectile.class)) {
                checkClass = IProjectile.class;
            }
            if (!player.hasComponent(checkClass)) {
                group.addChoice(option);
                added++;
            }

            if (added >= 3) {
                break;
            }
        }
        return group;
    }

    @Override
    public void render(float delta) {
        boolean allReady = true;
        for (UpgradeGroup group : groups) {
            group.update(delta);
            if (!group.isReady()) {
                allReady = false;
            }
        }
        if (allReady) {
            music.stop();
            game.setScreen(new RaceScreen(game));
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
