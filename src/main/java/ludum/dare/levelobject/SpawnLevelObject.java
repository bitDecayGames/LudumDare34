package ludum.dare.levelobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.gdx.level.RenderableLevelObject;
import com.bitdecay.jump.level.LevelObject;
import com.bitdecay.jump.leveleditor.render.LevelEditor;

/**
 * Created by Admin on 12/12/2015.
 */
public class SpawnLevelObject extends RenderableLevelObject {

    private final TextureRegion texture;

    public SpawnLevelObject() {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(LevelEditor.EDITOR_ASSETS_FOLDER + "/question.png")));

    }
    @Override
    public BitBody buildBody() {
        return null;
    }

    @Override
    public String name() {
        return "Spawn Point";
    }

    @Override
    public TextureRegion texture() {
        return texture;
    }
}
