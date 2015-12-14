package ludum.dare.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;
import com.bytebreakstudios.animagic.texture.AnimagicSpriteBatch;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;
import ludum.dare.util.LightUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LightGameObject extends BasePlacedObject {

    @Override
    public List<BitBody> build(LevelObject levelObject) {
        size = new SizeComponent(1, 1);
        pos = new PositionComponent(levelObject.rect.center().x, levelObject.rect.center().y);
        append(size).append(pos);
        return Collections.emptyList();
    }

    @Override
    public void draw(AnimagicSpriteBatch spriteBatch) {
        LightUtil.addLocatedLight(spriteBatch, new Vector2(pos.x, pos.y));
    }
}
