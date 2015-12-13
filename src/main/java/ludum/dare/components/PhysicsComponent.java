package ludum.dare.components;

import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.Facing;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

public class PhysicsComponent implements IComponent, IUpdate {

    private PositionComponent pos;
    private SizeComponent size;
    private BitBody body;
    private AnimationComponent animationComponent = null;

    public PhysicsComponent(BitBody body, PositionComponent pos, SizeComponent size){
        this.body = body;
        this.pos = pos;
        this.size = size;
    }

    public PhysicsComponent(BitBody body, PositionComponent pos, SizeComponent size, AnimationComponent animation) {
        this.body = body;
        this.pos = pos;
        this.size = size;
        this.animationComponent = animation;
    }

    public BitBody getBody(){
        return body;
    }

    @Override
    public void update(float delta) {
        pos.x = body.aabb.xy.x;
        pos.y = body.aabb.xy.y;
        size.set(body.aabb.width, body.aabb.height);

        // yes... I know, its ugly... but screw it, it works
        if (animationComponent != null) animationComponent.setFlipVerticalAxis(body.facing == Facing.LEFT);
    }
}
