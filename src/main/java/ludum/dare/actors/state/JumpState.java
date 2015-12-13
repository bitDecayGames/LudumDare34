package ludum.dare.actors.state;

import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class JumpState extends AbstractState {

    public JumpState(Set<IComponent> components) {
        super(components, null);
    }

    public void enter() {
        animationComponent.animator.switchToAnimation("jump");
    }

    public void exit() {

    }

    public IState update(float delta) {
        super.update(delta);

        if (physicsComponent.getBody().grounded) {
            if (inputComponent.isPressed(PlayerAction.RIGHT) || inputComponent.isPressed(PlayerAction.LEFT)) {
                return new RunState(components);
            } else {
                return new StandState(components);
            }
        }
        return null;
    }
}
