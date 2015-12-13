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
        Facing facing = physicsComponent.getBody().facing;
        switch (facing) {
            case LEFT:
                animationComponent.setFlipVerticalAxis(true);
                break;
            case RIGHT:
                animationComponent.setFlipVerticalAxis(false);
                break;
            default:
                throw new Error("Invalid facing set");
        }

        if (!inputComponent.isPressed(PlayerAction.RIGHT) &&
                !inputComponent.isPressed(PlayerAction.LEFT)) return new StandState(components);
        else if (inputComponent.isJustPressed(PlayerAction.JUMP)) return new JumpState(components);
        return null;
    }
}
