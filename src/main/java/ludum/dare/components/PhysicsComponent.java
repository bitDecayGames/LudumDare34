package ludum.dare.components;

import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.properties.BitBodyProperties;
import com.bitdecay.jump.properties.JumperProperties;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

public class PhysicsComponent implements IComponent, IUpdate {

    private final PositionComponent position;
    private final SizeComponent size;
    private JumperBody body;

    public PhysicsComponent(PositionComponent position, SizeComponent size, JumperBody body){
        this.position = position;
        this.size = size;
        this.body = body;
    }

    public BitBodyProperties getBodyProperties(){
        return body.props;
    }

    public JumperProperties getJumpProperties(){
        return body.jumperProps;
    }

    @Override
    public void update(float delta) {

    }
}
