package ludum.dare.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;

public class TextureRegionComponent implements IComponent, IDraw {

    private final PositionComponent position;
    private final SizeComponent size;
    public final AnimagicTextureRegion region;

    public TextureRegionComponent(AnimagicTextureRegion region, PositionComponent position, SizeComponent size) {
        this.position = position;
        this.size = size;
        this.region = region;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(region,
                position.x,
                position.y,
                size.w,
                size.h);
    }
}
