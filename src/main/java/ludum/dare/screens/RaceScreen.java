package ludum.dare.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.bitdecay.jump.leveleditor.EditorHook;
import com.bitdecay.jump.leveleditor.example.game.GravityField;
import com.bitdecay.jump.leveleditor.example.game.SecretObject;
import com.bitdecay.jump.leveleditor.example.game.ShellObject;
import com.bitdecay.jump.leveleditor.example.level.GravityLvlObject;
import com.bitdecay.jump.leveleditor.example.level.SecretThing;
import com.bitdecay.jump.leveleditor.example.level.ShellLevelObject;
import com.bitdecay.jump.render.JumperRenderStateWatcher;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.RacerGame;
import ludum.dare.actors.GameObject;
import ludum.dare.levels.LevelSegmentAggregator;
import ludum.dare.levels.LevelSegmentGenerator;

import java.util.*;

public class RaceScreen implements Screen, EditorHook {

    OrthographicCamera[] cameras = new OrthographicCamera[4];
    AnimagicSpriteBatch batch;

    Map<Class, Class> builderMap = new HashMap<>();

    List<GameObject> gameObjects = new ArrayList<>();
    List<GameObject> players = new ArrayList<>();


    public RaceScreen(List<GameObject> players) {
        this.players = players;
    }

    BitWorld world = new BitWorld();
    Level currentLevel = new Level();

    public RaceScreen(RacerGame game) {
        LevelSegmentGenerator generator = new LevelSegmentGenerator(10);
        Level raceLevel = LevelSegmentAggregator.assembleSegments(generator.generateLevelSegments());
        levelChanged(raceLevel);
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

    @Override
    public void render(OrthographicCamera cam) {

    }

    @Override
    public BitWorld getWorld() {
        return world;
    }

    @Override
    public List<EditorIdentifierObject> getTilesets() {
        return null;
    }

    @Override
    public List<EditorIdentifierObject> getThemes() {
        return null;
    }

    @Override
    public List<RenderableLevelObject> getCustomObjects() {
//        builderMap.put(SecretThing.class, SecretObject.class);
//        builderMap.put(ShellLevelObject.class, ShellObject.class);
//        builderMap.put(GravityLvlObject.class, GravityField.class);
        List<RenderableLevelObject> exampleItems = new ArrayList<>();
//        exampleItems.add(new SecretThing());
//        exampleItems.add(new ShellLevelObject());
//        exampleItems.add(new GravityLvlObject());
        return exampleItems;
    }

    public void draw(){
        gameObjects.forEach(obj -> obj.draw(batch));
    }

    @Override
    public void levelChanged(Level level) {
        gameObjects.clear();

        currentLevel = level;
        world.setTileSize(16);
        world.setGridOffset(level.gridOffset);
        world.setGrid(level.gridObjects);
        world.setTileSize(level.tileSize);
        world.setObjects(buildBodies(level.otherObjects));
        world.resetTimePassed();

        if (level.debugSpawn != null) {
            JumperBody playerBody = new JumperBody();
            playerBody.props = level.debugSpawn.props;
            playerBody.jumperProps = level.debugSpawn.jumpProps;

            playerBody.bodyType = BodyType.DYNAMIC;
            playerBody.aabb = new BitRectangle(level.debugSpawn.rect.xy.x,level.debugSpawn.rect.xy.y,16,32);
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
                    gameObjects.add(newObject);
                } else {
                    bodies.add(levelObject.buildBody());
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
