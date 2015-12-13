package ludum.dare.actors.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class PunchState extends IState {

    public PunchState(Set<IComponent> components, IState returnState) {
        super(components, returnState);
    }

    public void enter() {
    }

    public void exit() {

    }

    public IState update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) return new StandState(components);
        return null;
    }
}
