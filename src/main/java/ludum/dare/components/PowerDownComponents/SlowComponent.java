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
        this.phys.getBody().props.maxVoluntarySpeed = this.phys.getBody().props.maxVoluntarySpeed - 100;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void remove(){
        this.phys.getBody().props.maxVoluntarySpeed = this.phys.getBody().props.maxVoluntarySpeed + 100;
    }
}
