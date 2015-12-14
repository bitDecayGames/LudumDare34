package ludum.dare.components;

import com.badlogic.gdx.math.Vector2;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;
import ludum.dare.util.LightUtil;

/**
 * Created by Admin on 12/13/2015.
 */
public class LightComponent implements IComponent, IDraw {

    private final PositionComponent pos;

    public LightComponent(PositionComponent pos) {
        this.pos = pos;
    }

    @Override
    public void draw(AnimagicSpriteBatch spriteBatch) {
        LightUtil.addLocatedLight(spriteBatch, new Vector2(pos.x, pos.y));
    }
}
