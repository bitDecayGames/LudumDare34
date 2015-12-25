package ludum.dare.levelobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.annotation.ValueRange;
import com.bitdecay.jump.gdx.level.RenderableLevelObject;
import com.bitdecay.jump.geom.BitRectangle;
import com.bitdecay.jump.level.LevelObject;

public class AINodeLevelObject extends RenderableLevelObject {
    private static TextureRegion texture;

    @ValueRange(min = 0, max = 100)
    public int nodeIndex = 0;

    public AINodeLevelObject() {
        rect = new BitRectangle(0, 0, 20, 20);
    }

    @Override
    public TextureRegion texture() {
        if (texture == null) {
            texture = new TextureRegion(new Texture(Gdx.files.internal("ui/button.9.png")));
        }
        return texture;
    }

    @Override
    public BitBody buildBody() {
        return null;
    }

    @Override
    public String name() {
        return "AiNode";
    }
}
