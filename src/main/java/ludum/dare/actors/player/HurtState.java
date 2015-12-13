package ludum.dare.actors.player;

import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.AbstractState;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class HurtState extends AbstractState {

    public HurtState(Set<IComponent> components, IState returnState) {
        super(components, returnState);
    }

    public void enter() {
        animationComponent.animator.switchToAnimation("knockback");
    }

    public void exit() {

    }

    public IState update(float delta) {
        super.update(delta);
        return null;
    }
}
