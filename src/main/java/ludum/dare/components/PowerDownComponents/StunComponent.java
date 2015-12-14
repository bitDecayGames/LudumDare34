package ludum.dare.components.PowerDownComponents;

import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class StunComponent extends TimedComponent{
    private PhysicsComponent phys;

    public StunComponent(PhysicsComponent phys){
        super(1);
        this.phys = phys;

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void remove(){

    }
}
