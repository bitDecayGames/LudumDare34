package ludum.dare.actors.player;

import com.bitdecay.jump.Facing;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class JumpState extends IState {

    public JumpState(Set<IComponent> components) {
        super(components, null);
    }

    public void enter() {
        animationComponent.animator.switchToAnimation("jump");
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

        if (physicsComponent.getBody().grounded) return new StandState(components);
//        if (inputComponent.isPressed(InputAction.JUMP)) return new HurtState(components, this);
        return null;
    }
}
