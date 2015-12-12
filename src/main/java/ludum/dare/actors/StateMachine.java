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


    @Override
    public void update(float delta) {
        if (activeState != null) {
            IState newState = activeState.update(delta);
            if (newState != null) {
                activeState.exit();
                newState.enter();
                activeState = newState;
            }
        }
        super.update(delta);
    }
}
