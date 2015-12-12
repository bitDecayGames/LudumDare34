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
    private JumperBody jBody;

    public PhysicsComponent(JumperBody jBody, PositionComponent pos, SizeComponent size){
        this.jBody = jBody;
        this.pos = pos;
        this.size = size;
    }

    public JumperBody getjBody(){
        return jBody;
    }

    @Override
    public void update(float delta) {
        pos.x = jBody.aabb.xy.x;
        pos.y = jBody.aabb.xy.y;
        size.h = jBody.aabb.height;
        size.w = jBody.aabb.width;

    }
}
