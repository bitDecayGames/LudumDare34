package ludum.dare.components.PowerDownComponents;

import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class SlowComponent extends TimedComponent{
    PhysicsComponent phys;

    public SlowComponent(PhysicsComponent phys){
        super(5);
        this.phys = phys;

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void remove(){

    }
}
