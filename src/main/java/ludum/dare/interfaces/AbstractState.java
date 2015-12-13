package ludum.dare.interfaces;

import ludum.dare.components.AnimationComponent;
import ludum.dare.components.InputComponent;
import ludum.dare.components.PhysicsComponent;

import java.util.Set;

public abstract class AbstractState implements IState{
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
}
