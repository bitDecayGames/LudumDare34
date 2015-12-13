package ludum.dare.actors.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IState;

import java.util.Set;

public class HurtState extends IState {

    public HurtState(Set<IComponent> components, IState returnState) {
        super(components, returnState);
    }

    public void enter() {
        animationComponent.animator.switchToAnimation("knockback");
    }

    public void exit() {

    }

    public IState update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) return new StandState(components);
        return null;
    }
}
