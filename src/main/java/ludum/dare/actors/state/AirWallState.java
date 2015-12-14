package ludum.dare.actors.state;

import ludum.dare.interfaces.IComponent;

import java.util.Set;

public class AirWallState extends AbstractState {

    public AirWallState(Set<IComponent> components) {
        super(components);
    }

    public void enter() {
        super.enter();
        animationComponent.animator.switchToAnimation("wall");
    }

    public void exit() {
        super.exit();
    }
}
