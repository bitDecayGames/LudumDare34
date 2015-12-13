package ludum.dare.actors.state;

import com.bitdecay.jump.Facing;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.InputComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public abstract class AbstractState implements IState {
    protected Set<IComponent> components;
    protected PhysicsComponent physicsComponent;
    protected AnimationComponent animationComponent;
    protected InputComponent inputComponent;
    protected IState returnState = null;

    public AbstractState(Set<IComponent> components, IState returnState) {
        this.components = components;
        components.forEach(comp -> {
            if (comp instanceof PhysicsComponent) physicsComponent = (PhysicsComponent) comp;
            if (comp instanceof AnimationComponent) animationComponent = (AnimationComponent) comp;
            if (comp instanceof InputComponent) inputComponent = (InputComponent) comp;
        });
        this.returnState = returnState;
    }

    @Override
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

        return null;
    }
}
