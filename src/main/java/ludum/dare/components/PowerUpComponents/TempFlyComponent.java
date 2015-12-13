package ludum.dare.components.PowerUpComponents;

import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class TempFlyComponent extends TimedComponent{
    private PhysicsComponent phys;

    public TempFlyComponent() {
        super(5);
//        Alter physics to make fly, dunno how do
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void remove() {
//      Alter physics to remove fly, dunno how do
    }
}
