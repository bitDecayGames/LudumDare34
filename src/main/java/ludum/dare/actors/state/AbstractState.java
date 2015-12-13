package ludum.dare.actors.state;

import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.Facing;
import com.bitdecay.jump.common.RenderState;
import com.bitdecay.jump.common.StateListener;
import ludum.dare.components.AnimationComponent;
import ludum.dare.components.InputComponent;
import ludum.dare.components.PhysicsComponent;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public abstract class AbstractState implements IState, StateListener {
    protected Set<IComponent> components;
    protected PhysicsComponent physicsComponent;
    protected AnimationComponent animationComponent;
    protected InputComponent inputComponent;
    protected IState returnState = null;

    protected RenderState currentRenderState;
    protected RenderState previousRenderState;

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
    public void enter() {
        BitBody body = physicsComponent.getBody();
        currentRenderState = body.renderStateWatcher.getState();
        body.renderStateWatcher.addListener(this);
    }

    @Override
    public void exit() {
        physicsComponent.getBody().renderStateWatcher.removeListener(this);
    }

    @Override
    public IState update(float delta) {
        updateFacing();

        return null;
    }

    private void updateFacing() {
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
    }

    @Override
    public void stateChanged(RenderState state) {
        previousRenderState = currentRenderState;
        currentRenderState = state;
    }
}
