package ludum.dare.actors.player;

import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.AbstractState;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class PunchState extends AbstractState {

    public PunchState(Set<IComponent> components, IState returnState) {
        super(components, returnState);
    }

    public void enter() {
    }

    public void exit() {

    }

    public IState update(float delta) {
//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) return new StandState(components);
        return null;
    }
}
