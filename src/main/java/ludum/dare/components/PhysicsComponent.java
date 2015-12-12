package ludum.dare.components;

import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.properties.BitBodyProperties;
import com.bitdecay.jump.properties.JumperProperties;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

public class PhysicsComponent implements IComponent, IUpdate {

    private PositionComponent pos;
    private SizeComponent size;
    private BitBody Body;

    public PhysicsComponent(BitBody Body, PositionComponent pos, SizeComponent size){
        this.Body = Body;
        this.pos = pos;
        this.size = size;
    }

    public BitBody getBody(){
        return Body;
    }

    @Override
    public void update(float delta) {
        pos.x = Body.aabb.xy.x;
        pos.y = Body.aabb.xy.y;
        size.h = Body.aabb.height;
        size.w = Body.aabb.width;

    }
}
