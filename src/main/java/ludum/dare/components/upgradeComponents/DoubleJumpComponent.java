package ludum.dare.components.upgradeComponents;

import com.bitdecay.jump.JumperBody;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.interfaces.IComponent;

/**
 * Created by jake on 12/12/2015.
 */
public class DoubleJumpComponent implements IComponent {
    public int cost = 0;

    public DoubleJumpComponent(PhysicsComponent phys){
        if(phys.getBody() instanceof JumperBody) {
            ((JumperBody) phys.getBody()).jumpsRemaining = 2;
        }
    }
}
