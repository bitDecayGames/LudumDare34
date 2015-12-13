package ludum.dare.actors.player;

import com.bitdecay.jump.control.PlayerAction;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class StandState extends IState {

    public StandState(Set<IComponent> components) {
        super(components, null);
    }

    public void enter() {
        animationComponent.animator.switchToAnimation("stand");
    }

    public void exit() {

    }

    public IState update(float delta) {
        if (inputComponent.isJustPressed(PlayerAction.RIGHT) || inputComponent.isJustPressed(PlayerAction.LEFT)) return new RunState(components);
        return null;
    }
}
