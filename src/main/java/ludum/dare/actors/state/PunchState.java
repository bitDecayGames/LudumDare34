package ludum.dare.actors.state;

import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class PunchState extends AbstractState {

    public PunchState(Set<IComponent> components, IState returnState) {
        super(components, returnState);
    }

    public void enter() {
        super.enter();
    }

    public void exit() {
        super.exit();
    }

    public IState update(float delta) {
        return null;
    }
}
