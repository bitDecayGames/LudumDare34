package ludum.dare.components.PowerDownComponents;

import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class StunComponent extends TimedComponent{
    private PhysicsComponent phys;
    private int formerSpeed;

    public StunComponent(PhysicsComponent phys){
        super(1);
        this.phys = phys;
        formerSpeed = this.phys.getBody().props.maxVoluntarySpeed;
        this.phys.getBody().props.maxVoluntarySpeed = 0;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void remove(){
        phys.getBody().props.maxVoluntarySpeed = formerSpeed;
    }
}
