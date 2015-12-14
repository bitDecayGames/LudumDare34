package ludum.dare.levelobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.BodyType;
import com.bitdecay.jump.gdx.level.RenderableLevelObject;
import com.bitdecay.jump.geom.BitRectangle;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import ludum.dare.RacerGame;

/**
 * Created by Admin on 12/13/2015.
 */
public class PowerupLevelObject extends RenderableLevelObject {
    private TextureRegion texture;

    public PowerupLevelObject() {
        AnimagicTextureAtlas atlas = RacerGame.assetManager.get("packed/level.atlas", AnimagicTextureAtlas.class);

        this.texture = atlas.findRegion("collect/chest/closed/1");
        rect = new BitRectangle(0, 0, 28, 18);
    }

    @Override
    public TextureRegion texture() {
        return texture;
    }

    @Override
    public BitBody buildBody() {
        BitBody body = new BitBody();
        body.props.gravitational = false;
        body.props.collides = false;
        body.bodyType = BodyType.STATIC;
        body.aabb.set(new BitRectangle(rect));
        return body;
    }

    @Override
    public String name() {
        return "Powerup";
    }
}
