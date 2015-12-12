package ludum.dare.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytebreakstudios.animagic.animation.Animator;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;
import ludum.dare.interfaces.IUpdate;

public class AnimationComponent implements IComponent, IUpdate, IDraw {

    public final Animator animator;
    public final PhysicsComponent phys;

    public AnimationComponent(String name, PhysicsComponent phys){
        animator = new Animator(name);
        this.phys = phys;
    }

    @Override
    public void update(float delta) {
        animator.update(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(animator.getFrame(), position.x, position.y, size.w, size.h);
    }
}
