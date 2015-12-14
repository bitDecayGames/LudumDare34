package ludum.dare.components.PowerUpComponents;

import com.bitdecay.jump.JumperBody;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.components.TimedComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class TempFlyComponent extends TimedComponent{
    private PhysicsComponent phys;

    public TempFlyComponent(PhysicsComponent phys) {
        super(5);
        this.phys = phys;
        ((JumperBody) phys.getBody()).jumperProps.jumpCount += 10000;

    }


    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void remove() {
        ((JumperBody) phys.getBody()).jumperProps.jumpCount -= 10000;
    }
}
