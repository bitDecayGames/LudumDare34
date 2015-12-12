package ludum.dare.components;

import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.properties.BitBodyProperties;
import com.bitdecay.jump.properties.JumperProperties;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

public class PhysicsComponent implements IComponent, IUpdate {

    private BitBody body;
    private PositionComponent pos;
    private SizeComponent size;

    public PhysicsComponent(BitBody body, PositionComponent pos, SizeComponent size){
        this.body = body;
        this.pos = pos;
        this.size = size;
    }

    public BitBody getBody(){
        return body;
    }

    @Override
    public void update(float delta) {
        pos.x = body.aabb.xy.x;
        pos.y = body.aabb.xy.y;
        size.h = body.aabb.height;
        size.w = body.aabb.width;

    }
}
