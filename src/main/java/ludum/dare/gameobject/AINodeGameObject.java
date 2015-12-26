package ludum.dare.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.collision.ContactListener;
import com.bitdecay.jump.level.LevelObject;
import ludum.dare.actors.player.Player;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.PositionComponent;
import ludum.dare.components.SizeComponent;
import ludum.dare.levelobject.AINodeLevelObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AINodeGameObject extends BasePlacedObject {

    public int nodeIndex = 0;

    public boolean isTargeted = false;

    public LevelObject levelObject;

    @Override
    public List<BitBody> build(LevelObject levelObject) {
        size = new SizeComponent(1, 1);
        pos = new PositionComponent(levelObject.rect.xy.x, levelObject.rect.xy.y);

        nodeIndex = ((AINodeLevelObject) levelObject).nodeIndex;

        append(size).append(pos);

        this.levelObject = levelObject;

        return Collections.emptyList();
        //return Arrays.asList(phys.getBody());
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        if (isTargeted) shapeRenderer.setColor(Color.GREEN);
        else shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(pos.x, pos.y, 10);
    }

    public Vector2 getPosition() {
        return new Vector2(pos.x, pos.y);
    }

}
