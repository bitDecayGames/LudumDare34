package ludum.dare.actors.player;

import com.bitdecay.jump.Facing;
import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.AbstractState;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class RunState extends AbstractState {

    public RunState(Set<IComponent> components) {
        super(components, null);
    }

    public void enter() {
        animationComponent.animator.switchToAnimation("run");
    }

    public void exit() {

    }

    public IState update(float delta) {
        super.update(delta);

        if (!inputComponent.isPressed(PlayerAction.RIGHT) &&
                !inputComponent.isPressed(PlayerAction.LEFT)) return new StandState(components);
        else if (inputComponent.isJustPressed(PlayerAction.JUMP)) return new JumpState(components);
        return null;
    }
}
