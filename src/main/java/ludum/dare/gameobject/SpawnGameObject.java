package ludum.dare.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;
import ludum.dare.actors.GameObject;
import ludum.dare.components.PositionComponent;

import java.util.List;

/**
 * Created by Admin on 12/12/2015.
 */
public class SpawnGameObject extends GameObject {
    public PositionComponent pos = new PositionComponent(0, 0);
    @Override
    public List<BitBody> build(LevelObject levelObject) {
        pos.x = levelObject.rect.xy.x;
        pos.y = levelObject.rect.xy.y;
        return super.build(levelObject);
    }
}
