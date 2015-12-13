package ludum.dare.actors.player;

import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.AbstractState;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class StandState extends AbstractState {

    public StandState(Set<IComponent> components) {
        super(components, null);
    }

    public void enter() {
        animationComponent.animator.switchToAnimation("stand");
    }

    public void exit() {

    }

    public IState update(float delta) {
        super.update(delta);
        if (inputComponent.isJustPressed(PlayerAction.RIGHT) || inputComponent.isJustPressed(PlayerAction.LEFT)) return new RunState(components);
        else if (inputComponent.isJustPressed(PlayerAction.JUMP)) return new JumpState(components);
        return null;
    }
}
