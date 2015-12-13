package ludum.dare.actors.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class StandState extends IState {

    public StandState(Set<IComponent> components) {
        super(components);
    }

    public void enter() {
        // TODO: switch to a standing animation (some how figure out the last facing direction?
    }

    public void exit() {

    }

    public IState update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) return new RunState(components);
        return null;
    }
}
