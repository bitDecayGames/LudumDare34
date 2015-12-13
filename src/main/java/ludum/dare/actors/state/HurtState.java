package ludum.dare.actors.state;

import com.badlogic.gdx.math.Vector2;
import ludum.dare.components.HurtComponent;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class HurtState extends AbstractState {

    private HurtComponent hurt;

    public HurtState(Set<IComponent> components) {
        super(components);
    }

    public void enter() {
        hurt = new HurtComponent(inputComponent, 1, physicsComponent, 1, new Vector2(1, 1));
        animationComponent.animator.switchToAnimation("knockback");
    }

    public void exit() {
        hurt.remove();
    }

    public IState update(float delta) {
        hurt.update(delta);
        if (hurt.isTimerDone()) return getJumpState();
        return null;
    }
}
