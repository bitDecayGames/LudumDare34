package ludum.dare.actors.state;

import ludum.dare.interfaces.IComponent;
import ludum.dare.util.SoundLibrary;

import java.util.Set;

public class JumpState extends AbstractState {

    public JumpState(Set<IComponent> components) {
        super(components);
    }

    public void enter() {
        super.enter();
        animationComponent.animator.switchToAnimation("jump");
    }

    public void exit() {
        super.exit();
    }
}
