package ludum.dare.actors.player;

import ludum.dare.control.InputAction;
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

        if (physicsComponent.getBody().grounded) return new StandState(components);
        else if (inputComponent.isJustPressed(InputAction.PUNCH)) return new PunchState(components, this);
        else if (inputComponent.isJustPressed(InputAction.PROJECTILE)) return new HurtState(components, this);
        return null;
    }
}
