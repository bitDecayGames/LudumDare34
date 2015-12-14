package ludum.dare.components.PowerDownComponents;

import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.TimedComponent;
import ludum.dare.interfaces.IComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class ForceHighJumpComponent extends TimedComponent{

    public ForceHighJumpComponent(PhysicsComponent phys){
        super(1);

    }

    @Override
    public void remove(){
    }
}
