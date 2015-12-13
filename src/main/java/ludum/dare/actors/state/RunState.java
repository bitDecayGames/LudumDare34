package ludum.dare.actors.state;

import ludum.dare.interfaces.IComponent;

import java.util.Set;

public class RunState extends AbstractState {

    public RunState(Set<IComponent> components) {
        super(components);
    }

    public void enter() {
        super.enter();
        animationComponent.animator.switchToAnimation("run");
    }

    public void exit() {
        super.exit();
    }
}
