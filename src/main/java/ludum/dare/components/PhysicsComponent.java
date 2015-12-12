package ludum.dare.components;

import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.properties.BitBodyProperties;
import com.bitdecay.jump.properties.JumperProperties;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

public class PhysicsComponent implements IComponent, IUpdate {

    private BitBody body;

    public PhysicsComponent(BitBody body){
        this.body = body;
    }

    public BitBodyProperties getBody(){
        return body.props;
    }

    @Override
    public void update(float delta) {

    }
}
