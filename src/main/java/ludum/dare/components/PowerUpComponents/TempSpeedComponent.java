package ludum.dare.components.PowerUpComponents;

import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class TempSpeedComponent extends TimedComponent {
    private PhysicsComponent phys;

    public TempSpeedComponent(PhysicsComponent phys){
        super(5);
        this.phys = phys;
        this.phys.getBody().props.acceleration = this.phys.getBody().props.acceleration + 300;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void remove(){
        phys.getBody().props.acceleration = phys.getBody().props.acceleration - 300;
    }
}
