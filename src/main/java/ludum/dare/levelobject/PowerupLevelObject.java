package ludum.dare.levelobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.gdx.level.RenderableLevelObject;
import com.bitdecay.jump.geom.BitRectangle;

/**
 * Created by Admin on 12/13/2015.
 */
public class PowerupLevelObject extends RenderableLevelObject {
    private TextureRegion texture;

    public PowerupLevelObject() {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal("ui/button.9.png")));
        rect = new BitRectangle(0, 0, 16 ,16);
    }

    @Override
    public TextureRegion texture() {
        return texture;
    }

    @Override
    public BitBody buildBody() {
        return null;
    }

    @Override
    public String name() {
        return "Powerup";
    }
}
