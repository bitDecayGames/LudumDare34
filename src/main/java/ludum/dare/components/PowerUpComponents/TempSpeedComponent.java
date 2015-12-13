package ludum.dare.components.PowerUpComponents;

import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.upgradeComponents.TimedComponent;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IUpdate;

/**
 * Created by jake on 12/12/2015.
 */
public class TempSpeedComponent extends TimedComponent {
    private PhysicsComponent phys;

    public TempSpeedComponent(PhysicsComponent phys){
        super(5);
        this.phys = phys;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
