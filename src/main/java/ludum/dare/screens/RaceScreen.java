package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.BodyType;
import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.collision.BitWorld;
import com.bitdecay.jump.collision.ContactAdapter;
import com.bitdecay.jump.control.PlayerInputController;
import com.bitdecay.jump.gdx.input.GDXControls;
import com.bitdecay.jump.gdx.level.EditorIdentifierObject;
import com.bitdecay.jump.gdx.level.RenderableLevelObject;
import com.bitdecay.jump.geom.BitRectangle;
import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.level.LevelObject;
import com.bitdecay.jump.level.TileObject;
import com.bitdecay.jump.leveleditor.EditorHook;
import com.bitdecay.jump.leveleditor.example.game.SecretObject;
import com.bitdecay.jump.leveleditor.render.LibGDXWorldRenderer;
import com.bitdecay.jump.render.JumperRenderStateWatcher;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.RacerGame;
import ludum.dare.actors.GameObject;
import ludum.dare.actors.player.Player;
import ludum.dare.collection.GameObjects;
import ludum.dare.components.LevelInteractionComponent;
import ludum.dare.control.InputUtil;
import ludum.dare.control.Xbox360Pad;
import ludum.dare.gameobject.*;
import ludum.dare.levelobject.*;
import ludum.dare.levels.LevelSegmentAggregator;
import ludum.dare.levels.LevelSegmentGenerator;
import ludum.dare.util.Players;
import ludum.dare.util.SoundLibrary;

import java.util.*;

public class RaceScreen implements Screen, EditorHook {
    RacerGame game;

    private Music music;

    OrthographicCamera[] cameras;
    AnimagicSpriteBatch batch;
    SpriteBatch ui;
    LibGDXWorldRenderer worldRenderer = new LibGDXWorldRenderer();

    Map<Class, Class> builderMap = new HashMap<>();

    Map<Integer, TextureRegion[]> tilesetMap = new HashMap<>();

    BitWorld world = new BitWorld();
    Level currentLevel = new Level();
    GameObjects gameObjects = new GameObjects();

    TextureRegion splitScreenSeparator;

    public RaceScreen(RacerGame game) {
        if (game == null) {
            throw new Error("No game provided");
        }

        constructBuilderMap();

        world.setGravity(0, -700);

        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/tiles.atlas", AnimagicTextureAtlas.class);
        Array<AnimagicTextureRegion> crystalTileTextures = atlas.findRegions("crystal");
        Array<AnimagicTextureRegion> bridgesTileTextures = atlas.findRegions("bridges");
        tilesetMap.put(0, crystalTileTextures.toArray(TextureRegion.class));
        tilesetMap.put(1, bridgesTileTextures.toArray(TextureRegion.class));

        atlas = RacerGame.assetManager.get("packed/ui.atlas", AnimagicTextureAtlas.class);
        splitScreenSeparator = atlas.findRegion("splitscreenSeparator");

        this.game = game;
        cameras = new OrthographicCamera[Players.list().size()];

        generateNextLevel(10);
    }

    public void generateNextLevel(int length) {
        LevelSegmentGenerator generator = new LevelSegmentGenerator(length);
        Level raceLevel = LevelSegmentAggregator.assembleSegments(generator.generateLevelSegments());
        levelChanged(raceLevel);
    }

    private void constructBuilderMap() {
        builderMap.put(SpawnLevelObject.class, SpawnGameObject.class);
        builderMap.put(CoinLevelObject.class, CoinGameObject.class);
        builderMap.put(FinishLineLevelObject.class, FinishLineGameObject.class);
        builderMap.put(PowerupLevelObject.class, PowerupGameObject.class);
        builderMap.put(LightLevelObject.class, LightGameObject.class);
    }

    @Override
    public void show() {

        music = SoundLibrary.GetMusic("fight");

        if(RacerGame.MUSIC_ON) {
            music.play();
            music.setLooping(true);
        }

        for (int i = 0; i < cameras.length; i++)
            cameras[i] = new OrthographicCamera(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        batch = new AnimagicSpriteBatch();
        batch.isShaderOn(true);

        ui = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        update(delta);

        draw();
    }

    public void update(float delta){
        world.step(delta);
        gameObjects.update(delta);

        updateCameras(delta);

        // Reset level
        if (InputUtil.checkInputs(Input.Keys.R, Xbox360Pad.BACK)) {
            game.setScreen(new RaceScreen(game));
        }
    }

    private void updateCameras(float delta) {
        for (int i = 0; i < cameras.length; i++) {
            Camera cam = cameras[i];
            // Follow player
            Vector3 playerPos = Players.list().get(i).getPosition();
            cam.position.set(playerPos);
            cam.update();
        }
    }

    @Override
    public void render(OrthographicCamera cam) {
        for(OrthographicCamera playerCam : cameras) {
            playerCam.position.set(cam.position);
            playerCam.zoom = cam.zoom;
            playerCam.viewportWidth = cam.viewportWidth;
            playerCam.viewportHeight = cam.viewportHeight;
            playerCam.projection.set(cam.projection);

            playerCam.update();
        }
        draw();
    }

    @Override
    public BitWorld getWorld() {
        return world;
    }

    @Override
    public List<EditorIdentifierObject> getTilesets() {
        return Arrays.asList(new EditorIdentifierObject(0, "Fallback", tilesetMap.get(0)[0]),
                new EditorIdentifierObject(1, "Bridges", tilesetMap.get(1)[0]));
    }

    @Override
    public List<EditorIdentifierObject> getThemes() {
        return Collections.emptyList();
    }

    @Override
    public List<RenderableLevelObject> getCustomObjects() {
        List<RenderableLevelObject> exampleItems = new ArrayList<>();
        exampleItems.add(new SpawnLevelObject());
        exampleItems.add(new CoinLevelObject());
        exampleItems.add(new FinishLineLevelObject());
        exampleItems.add(new PowerupLevelObject());
        exampleItems.add(new LightLevelObject());
        return exampleItems;
    }

    private void draw(){
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int screenWidth = Gdx.graphics.getWidth() / 2;
        int screenHeight = Gdx.graphics.getHeight() / 2;

        Gdx.gl.glViewport(0, 0, screenWidth, screenHeight);
        draw(cameras[0]);
        Gdx.gl.glViewport(screenWidth, 0, screenWidth, screenHeight);
        draw(cameras[1]);
        Gdx.gl.glViewport(0, screenHeight, screenWidth, screenHeight);
        draw(cameras[2]);
        Gdx.gl.glViewport(screenWidth, screenHeight, screenWidth, screenHeight);
        draw(cameras[3]);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ui.begin();
        ui.draw(splitScreenSeparator, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ui.end();

//        worldRenderer.render(world, cameras[0]);
    }

    private void draw(Camera cam) {
        batch.setCamera(cam);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        Vector3 mousePos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        batch.setAmbientColor(Color.WHITE);
        batch.setAmbientIntensity(0.01f);
        batch.setNextLight(mousePos.x, mousePos.y, 0.1f, 0.9f, Color.WHITE);

        drawLevelEdit();
        gameObjects.draw(batch);
        batch.end();
    }

    private void drawLevelEdit() {
        /**
         * TODO: we still need to find a better way to load a grid into the world but with custom tile objects.
         * It shouldn't be hard, but it does need to be done.
         **/
        for (int x = 0; x < currentLevel.gridObjects.length; x++) {
            for (int y = 0; y < currentLevel.gridObjects[0].length; y++) {
                TileObject obj = currentLevel.gridObjects[x][y];
                if (obj != null) {
                    batch.draw(tilesetMap.get(obj.material)[obj.renderNValue], obj.rect.xy.x, obj.rect.xy.y, obj.rect.width, obj.rect.height);
                }
            }
        }
    }

    @Override
    public void levelChanged(Level level) {
        gameObjects.clear();
        world.removeAllBodies();

        currentLevel = level;
        world.setTileSize(16);
        world.setGridOffset(level.gridOffset);
        world.setGrid(level.gridObjects);
        world.setTileSize(level.tileSize);
        world.setObjects(buildBodies(level.otherObjects));
        world.resetTimePassed();

        boolean spawnFound = false;
        Iterator<GameObject> iter = gameObjects.getIter();
        GameObject object;
        while (iter.hasNext()) {
            object = iter.next();
            if (object instanceof SpawnGameObject) {
                spawnFound = true;
                SpawnGameObject spawn = (SpawnGameObject) object;
                for (Player player : Players.list()) {
                    player.addToScreen(new LevelInteractionComponent(world, gameObjects));
                    player.setPosition(spawn.pos.x, spawn.pos.y);
                }
            }
        }

        if (!spawnFound) {
            for (Player player : Players.list()) {
                player.activateControls();
                player.addToScreen(new LevelInteractionComponent(world, gameObjects));
                // TODO handle spawn points.
                player.setPosition(0, 0);
            }
        }

        if (level.debugSpawn != null) {
            JumperBody playerBody = new JumperBody();
            playerBody.props = level.debugSpawn.props;
            playerBody.jumperProps = level.debugSpawn.jumpProps;

            playerBody.bodyType = BodyType.DYNAMIC;
            playerBody.aabb = new BitRectangle(level.debugSpawn.rect.xy.x, level.debugSpawn.rect.xy.y, 16, 32);
            playerBody.renderStateWatcher = new JumperRenderStateWatcher();
            playerBody.controller = new PlayerInputController(GDXControls.defaultMapping);

            playerBody.addContactListener(new ContactAdapter() {
                @Override
                public void contactStarted(BitBody other) {
                    if (other.userObject instanceof SecretObject) {
                        playerBody.props.gravityModifier = -1;
                    }
                }

                @Override
                public void contactEnded(BitBody other) {
                    if (other.userObject instanceof SecretObject) {
                        playerBody.props.gravityModifier = 1;
                    }
                }
            });

            world.addBody(playerBody);
        }
    }

    private Collection<BitBody> buildBodies(Collection<LevelObject> otherObjects) {
        try {
            ArrayList<BitBody> bodies = new ArrayList<>();
            for (LevelObject levelObject : otherObjects) {
                if (builderMap.containsKey(levelObject.getClass())) {
                    GameObject newObject;
                    newObject = ((GameObject) builderMap.get(levelObject.getClass()).newInstance());
                    bodies.addAll(newObject.build(levelObject));
                    newObject.append(new LevelInteractionComponent(world, gameObjects));
                    gameObjects.add(newObject);
                } else {
                    throw new RuntimeException("Found object that doesn't have mapping: " + levelObject);
                }
            }
            return bodies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
