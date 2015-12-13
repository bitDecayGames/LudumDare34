package ludum.dare.actors.state;

import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.control.InputAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class RunState extends AbstractState {

    public RunState(Set<IComponent> components) {
        super(components, null);
    }

    public void enter() {
        super.enter();
        animationComponent.animator.switchToAnimation("run");
    }

    public void exit() {
        super.exit();
    }

    public IState update(float delta) {
        if (!inputComponent.isPressed(PlayerAction.RIGHT) &&
                !inputComponent.isPressed(PlayerAction.LEFT)) return new StandState(components);
        else if (inputComponent.isJustPressed(PlayerAction.JUMP)) return new JumpState(components);
        else if (inputComponent.isJustPressed(InputAction.PUNCH)) return new PunchState(components, this);
        return null;
    }
}
