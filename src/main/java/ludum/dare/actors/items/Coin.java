package ludum.dare.actors.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.level.LevelObject;
import com.bitdecay.jump.leveleditor.example.game.GameObject;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.CollectableComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PositionComponent;

import java.text.CollationElementIterator;
import java.util.List;

/**
 * Created by jake on 12/11/2015.
 */
public class Coin extends GameObject{

    private PhysicsComponent phys;
    private AnimationComponent anim;
    private CollectableComponent collect;

    @Override
    public List<BitBody> build(LevelObject template) {
        return null;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
