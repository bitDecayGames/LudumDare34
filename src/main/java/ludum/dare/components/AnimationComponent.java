package ludum.dare.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytebreakstudios.animagic.animation.Animator;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;
import ludum.dare.interfaces.IUpdate;

public class AnimationComponent implements IComponent, IUpdate, IDraw {

    private final PositionComponent position;
    private final SizeComponent size;
    public final Animator animator;

    public AnimationComponent(String name, PositionComponent position, SizeComponent size){
        this.position = position;
        this.size = size;
        animator = new Animator(name);
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
