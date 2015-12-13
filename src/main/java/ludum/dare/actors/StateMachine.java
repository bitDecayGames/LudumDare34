package ludum.dare.actors;

import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

public class StateMachine extends GameObject {
    protected IState activeState = null;

    public StateMachine() {
        super();
    }

    public StateMachine(IComponent... components) {
        super(components);
    }

    public void setActiveState(IState value) {
        if (activeState != null) {
            activeState.exit();
        }

        activeState = value;

        if (activeState != null) {
            activeState.enter();
        }
    }

    @Override
    public void update(float delta) {
        if (activeState != null) {
            IState newState = activeState.update(delta);
            if (newState != null) {
                setActiveState(newState);
            }
        }
        super.update(delta);
    }

    private void logStateTransition(IState currentState, IState newState) {
        System.out.println(currentState != null ? currentState.getClass() : "Nothing" + " -> " + newState != null ? newState.getClass() : "Nothing");
    }
}
