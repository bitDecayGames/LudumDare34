package ludum.dare.actors.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class RunState extends IState {

    public RunState(Set<IComponent> components) {
        super(components);
    }

    public void enter() {
        // TODO: switch to a running animation
    }

    public void exit() {

    }

    public IState update(float delta) {
        // TODO: switch the direction of the animation based on something

        if (!Gdx.input.isKeyPressed(Input.Keys.LEFT)) return new StandState(components);
        return null;
    }
}
