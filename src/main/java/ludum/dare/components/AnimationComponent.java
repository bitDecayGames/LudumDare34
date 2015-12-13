package ludum.dare.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bytebreakstudios.animagic.animation.Animator;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;
import ludum.dare.interfaces.IUpdate;

public class AnimationComponent implements IComponent, IUpdate, IDraw {

    public final Animator animator;
    private final PositionComponent position;
    private final SizeComponent size;

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
        TextureRegion reg = animator.getFrame();

        float ratio = size.h / reg.getRegionHeight();

        spriteBatch.draw(reg, position.x, position.y, reg.getRegionWidth() * ratio, reg.getRegionHeight() * ratio);
    }

}