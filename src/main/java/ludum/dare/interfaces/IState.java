package ludum.dare.interfaces;

import ludum.dare.components.AnimationComponent;
import ludum.dare.components.PhysicsComponent;

import java.util.Set;

public abstract class IState {
    protected Set<IComponent> components;
    protected PhysicsComponent physicsComponent;
    protected AnimationComponent animationComponent;

    public IState(Set<IComponent> components) {
        this.components = components;
        components.forEach(comp -> {
            if (comp instanceof PhysicsComponent) physicsComponent = (PhysicsComponent) comp;
            if (comp instanceof AnimationComponent) animationComponent = (AnimationComponent) comp;
        });
    }

    public abstract void enter();

    public abstract void exit();

    public abstract IState update(float delta);
}
