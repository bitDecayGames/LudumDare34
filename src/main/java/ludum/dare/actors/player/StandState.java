package ludum.dare.actors.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class StandState extends IState {

    public StandState(Set<IComponent> components) {
        super(components, null);
    }

    public void enter() {
        animationComponent.animator.switchToAnimation("stand");
    }

    public void exit() {

    }

    public IState update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) return new RunState(components);
        return null;
    }
}
