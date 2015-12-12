package ludum.dare.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bytebreakstudios.animagic.animation.Animator;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import ludum.dare.interfaces.IComponent;
import ludum.dare.interfaces.IDraw;
import ludum.dare.interfaces.IUpdate;

public class AnimationComponent implements IComponent, IUpdate, IDraw {

    private final PositionComponent position;
    private final SizeComponent size;
    public final Animator animator;
    TextureRegion region;

    public AnimationComponent(String name, PositionComponent position, SizeComponent size){
        this.position = position;
        this.size = size;
        animator = new Animator(name);

        FileHandle file = Gdx.files.internal(name);
        Texture texture = new Texture(file);
        region = new AnimagicTextureRegion(texture, AnimagicTextureAtlas.flatNormalMap);
    }

    @Override
    public void update(float delta) {
        animator.update(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        //animator.draw(spriteBatch);
        spriteBatch.draw(region, position.x, position.y, size.w, size.h);
    }
}
