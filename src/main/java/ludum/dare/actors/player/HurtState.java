package ludum.dare.actors.player;

import ludum.dare.components.upgradeComponents.TimedComponent;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class HurtState extends IState {

    private TimedComponent timer;

    public HurtState(Set<IComponent> components, IState returnState) {
        super(components, returnState);
    }

    public void enter() {
        timer = new TimedComponent(1);
        animationComponent.animator.switchToAnimation("knockback");
    }

    public void exit() {

    }

    public IState update(float delta) {
        timer.update(delta);
        if (timer.shouldRemove()) return returnState;
        return null;
    }
}
